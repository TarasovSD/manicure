package ru.manicure.service;

import ru.manicure.model.dto.comment.CommentDto;
import ru.manicure.model.dto.comment.CommentFullDto;

import java.util.List;

public interface CommentService {
    List<CommentFullDto> getAllByAppointmentId(Long appointmentId);

    CommentFullDto create(CommentDto commentDto);

    void remove(Long commentId);
}
