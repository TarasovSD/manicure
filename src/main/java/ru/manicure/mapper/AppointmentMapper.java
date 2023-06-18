package ru.manicure.mapper;

import lombok.experimental.UtilityClass;
import ru.manicure.model.*;
import ru.manicure.model.dto.appointment.AppointmentDto;
import ru.manicure.model.dto.appointment.AppointmentFullDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AppointmentMapper {

    public Appointment toAppointment(AppointmentDto appointmentDto, User client, User master, Procedure procedure,
                                     LocalDateTime created, Status status) {
        return new Appointment(appointmentDto.getId(),
                client,
                master,
                procedure,
                appointmentDto.getStart(),
                appointmentDto.getEnd(),
                created,
                status);

    }

    public AppointmentFullDto toAppointmentFullDto(Appointment appointment, List<Comment> comments) {

        AppointmentFullDto.InternalUser client = toInternalUser(appointment.getClient());
        AppointmentFullDto.InternalUser master = toInternalUser(appointment.getMaster());
        AppointmentFullDto.InternalProcedure procedure = toInternalProcedure(appointment.getProcedure());
        List<AppointmentFullDto.InternalComment> internalComments =
                comments != null && !comments.isEmpty()?toListOfInternalComments(comments):new ArrayList<>();

        return new AppointmentFullDto(appointment.getId(),
                client,
                master,
                procedure,
                appointment.getStart(),
                appointment.getEnd(),
                appointment.getCreated(),
                appointment.getStatus(),
                internalComments);
    }

    private AppointmentFullDto.InternalUser toInternalUser (User user) {
        return new AppointmentFullDto.InternalUser(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthday());
    }

    private AppointmentFullDto.InternalProcedure toInternalProcedure(Procedure procedure){
        return new AppointmentFullDto.InternalProcedure (procedure.getId(),
                procedure.getProcedureName(),
                procedure.getDescription());
    }

    private List<AppointmentFullDto.InternalComment> toListOfInternalComments (List<Comment> comments){
        return comments.stream().map(AppointmentMapper::toInternalComment).toList();
    }

    private AppointmentFullDto.InternalComment toInternalComment (Comment comment) {
        return new AppointmentFullDto.InternalComment(comment.getId(),
                comment.getUser().getId(),
                comment.getUserText(),
                comment.getCreated());
    }
}
