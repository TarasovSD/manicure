package ru.manicure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manicure.exceptions.AppointmentNotFoundException;
import ru.manicure.exceptions.CommentNotFoundException;
import ru.manicure.exceptions.UserNotFoundException;
import ru.manicure.mapper.CommentMapper;
import ru.manicure.model.Appointment;
import ru.manicure.model.Comment;
import ru.manicure.model.User;
import ru.manicure.model.dto.comment.CommentDto;
import ru.manicure.model.dto.comment.CommentFullDto;
import ru.manicure.repository.AppointmentRepository;
import ru.manicure.repository.CommentRepository;
import ru.manicure.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<CommentFullDto> getAllByAppointmentId(Long appointmentId) {
        List<Comment> comments = commentRepository.getCommentsByAppointmentIdOrderByCreated(appointmentId);
        return comments.stream().map(CommentMapper::toCommentFullDto).toList();
    }

    @Override
    @Transactional
    public CommentFullDto create(CommentDto commentDto) {
        String userMessage = "User id " + commentDto.getUserId() +  " not found";
        User user =  userRepository.findById(commentDto.getUserId())
                .orElseThrow(()-> new UserNotFoundException(userMessage));
        String appointmentMessage = "Appointment id " + commentDto.getAppointmentId() + " not found";
        Appointment appointment = appointmentRepository.findById(commentDto.getAppointmentId())
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentMessage));
        Comment commentToSave = CommentMapper.toComment(commentDto, user, appointment, LocalDateTime.now());
        return CommentMapper.toCommentFullDto(commentRepository.save(commentToSave));
    }

    @Override
    @Transactional
    public void remove(Long commentId) {
        String message = "Comment id " + commentId + " not found";
        Comment commentToDelete = commentRepository.findById(commentId).
                orElseThrow(() -> new CommentNotFoundException(message));
        commentRepository.delete(commentToDelete);
    }

}
