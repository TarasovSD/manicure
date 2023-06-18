package ru.manicure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manicure.exceptions.*;
import ru.manicure.mapper.AppointmentMapper;
import ru.manicure.model.*;
import ru.manicure.model.dto.appointment.AppointmentDto;
import ru.manicure.model.dto.appointment.AppointmentFullDto;
import ru.manicure.model.dto.appointment.AppointmentUpdateDto;
import ru.manicure.repository.AppointmentRepository;
import ru.manicure.repository.CommentRepository;
import ru.manicure.repository.ProcedureRepository;
import ru.manicure.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ProcedureRepository procedureRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public AppointmentFullDto create(AppointmentDto appointmentDto) {
        String clientMessage = "Client id " + appointmentDto.getClientId() + " not found";
        String masterMessage = "Master id " + appointmentDto.getMasterId() + " not found";
        String procedureMessage = "Procedure id " + appointmentDto.getProcedureId() + " not found";
        User client = userRepository.findById(appointmentDto.getClientId())
                .orElseThrow(() -> new UserNotFoundException(clientMessage));
        User master = userRepository.findById(appointmentDto.getMasterId())
                .orElseThrow(() -> new UserNotFoundException(masterMessage));

        if (!master.getIsMaster()) {
            String message = "User with id " + master.getId() + " isn't master";
            throw new UserIsNotMasterException(message);
        }

        if (Objects.equals(client.getId(), master.getId())) {
            String message = "Master and client id are the same, id = " + client.getId();
            throw new UsersIdsMatchException(message);
        }

        List<Appointment> intersections =
                appointmentRepository.getAppointmentsToSearchForIntersections(master.getId(), client.getId(),
                        appointmentDto.getStart(), appointmentDto.getEnd());

        if (!intersections.isEmpty()) {
            List<Long> ids = intersections.stream().map(Appointment::getId).toList();
            String message = "An appointment entry overlaps in time with other appointment entries. Ids: " + ids;
            throw new IntersectionOfAppointmentsException(message);
        }

        Procedure procedure = procedureRepository.findById(appointmentDto.getProcedureId())
                .orElseThrow(() -> new ProcedureNotFoundException(procedureMessage));
        LocalDateTime created = LocalDateTime.now();
        Appointment appointmentToSave = AppointmentMapper.toAppointment(appointmentDto,
                client, master, procedure, created, Status.CREATED);
        Appointment saved = appointmentRepository.save(appointmentToSave);

        Comment clientComment = new Comment(null,
                client,
                saved,
                appointmentDto.getClientComment(),
                created);

        commentRepository.save(clientComment);

        List<Comment> comments = commentRepository.getCommentsByAppointmentOrderByCreated(saved);

        return AppointmentMapper.toAppointmentFullDto(saved, comments);
    }

    @Override
    public List<AppointmentFullDto> getAllByMasterId(Long masterId, String time) {
        List<Appointment> appointments;
        LocalDateTime thisMoment = LocalDateTime.now();

        switch (time) {
            case "all" -> appointments = appointmentRepository.findAppointmentsByMasterIdOrderByCreated(masterId);
            case "future" ->
                    appointments = appointmentRepository.findAppointmentsByMasterIdInFuture(masterId, thisMoment);
            case "past" -> appointments = appointmentRepository.findAppointmentsByMasterIdInPast(masterId, thisMoment);
            default -> {
                String message = "The value of the variable time must be one: of all, future or past; but was: " + time;
                throw new UnknownTemporaryStatusException(message);
            }
        }

        List<Long> appointmentIds = appointments.stream().map(Appointment::getId).toList();
        List<Comment> allComments = commentRepository.getAllByAppointmentsId(appointmentIds);
        Map<Long, List<Comment>> mapAppointmentComment = new HashMap<>();

        for (Comment comment : allComments) {
            List<Comment> comments = new ArrayList<>();
            Long AppointmentId = comment.getAppointment().getId();
            if (mapAppointmentComment.containsKey(AppointmentId)) {
                comments = mapAppointmentComment.get(AppointmentId);
            }
            comments.add(comment);
            mapAppointmentComment.put(AppointmentId, comments);
        }

        List<AppointmentFullDto> appointmentFullDto = new ArrayList<>();
        for (Appointment appointment : appointments) {
            List<Comment> comments = mapAppointmentComment.get(appointment.getId());
            appointmentFullDto.add(AppointmentMapper.toAppointmentFullDto(appointment, comments));
        }
        return appointmentFullDto;
    }

    @Override
    public AppointmentFullDto getById(Long id) {
        String message = "Appointment id " + id + " not found";
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException(message));

        List<Comment> comments = commentRepository.getCommentsByAppointmentOrderByCreated(appointment);

        return AppointmentMapper.toAppointmentFullDto(appointment, comments);
    }

    @Override
    @Transactional
    public void removeById(Long appointmentId) {
        String message = "Appointment id " + appointmentId + " not found";
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(message));
        appointmentRepository.delete(appointment);
    }

    @Override
    @Transactional
    public AppointmentFullDto update(Long appointmentId, AppointmentUpdateDto appointmentUpdateDto) {
        String message = "Appointment id " + appointmentId + " not found";
        Appointment appointmentToUpdate = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(message));
        Long procedureId = appointmentUpdateDto.getProcedureId();

        if (procedureId != null) {
            String procedureMessage = "Procedure id " + procedureId + " not found";
            Procedure procedure = procedureRepository.findById(procedureId)
                    .orElseThrow(() -> new ProcedureNotFoundException(procedureMessage));
            appointmentToUpdate.setProcedure(procedure);
        }

        LocalDateTime start = appointmentUpdateDto.getStart();

        LocalDateTime end = appointmentUpdateDto.getEnd();

        if (start != null && end != null) {
            List<Appointment> intersections =
                    appointmentRepository.getAppointmentsToSearchForIntersections(appointmentToUpdate.getMaster().getId(),
                            appointmentToUpdate.getClient().getId(),
                            start, end);

            intersections.removeIf(appointment -> Objects.equals(appointment.getId(), appointmentId));

            if (!intersections.isEmpty()) {
                    List<Long> ids = intersections.stream().map(Appointment::getId).toList();
                    String intersectionsMessage =
                            "An appointment entry overlaps in time with other appointment entries. Ids: " + ids;
                    throw new IntersectionOfAppointmentsException(intersectionsMessage);
            }

            appointmentToUpdate.setStatus(Status.RESCHEDULED);
            appointmentToUpdate.setStart(start);
            appointmentToUpdate.setEnd(end);
        }

        List<Comment> comments = commentRepository.getCommentsByAppointmentOrderByCreated(appointmentToUpdate);

        return AppointmentMapper.toAppointmentFullDto(appointmentToUpdate, comments);
    }

    @Override
    @Transactional
    public AppointmentFullDto approveOrReject(Long appointmentId, String approveOrReject) {
        String message = "Appointment id " + appointmentId + " not found";
        Appointment appointmentToApproveOrReject = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(message));

        if (approveOrReject.equals("approve")){
            appointmentToApproveOrReject.setStatus(Status.APPROVED);
        } else if (approveOrReject.equals("reject")){
            appointmentToApproveOrReject.setStatus(Status.REJECTED);
        } else {
            String statementMessage = "The value of the variable approveOrReject must be one of: approve, reject; " +
                    "but was: " + approveOrReject;
            throw new UnknownStatusException(statementMessage);
        }
        return AppointmentMapper.toAppointmentFullDto(appointmentToApproveOrReject,
                commentRepository.getCommentsByAppointmentOrderByCreated(appointmentToApproveOrReject));
    }
}
