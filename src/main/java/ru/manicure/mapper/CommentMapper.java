package ru.manicure.mapper;

import lombok.experimental.UtilityClass;
import ru.manicure.model.Appointment;
import ru.manicure.model.Comment;
import ru.manicure.model.User;
import ru.manicure.model.dto.comment.CommentDto;
import ru.manicure.model.dto.comment.CommentFullDto;

import java.time.LocalDateTime;

@UtilityClass
public class CommentMapper {

    public Comment toComment (CommentDto commentDto, User user, Appointment appointment, LocalDateTime now) {
        return new Comment(commentDto.getId(),
                user,
                appointment,
                commentDto.getUserText(),
                now);
    }

    public CommentFullDto toCommentFullDto (Comment comment) {
        return new CommentFullDto(comment.getId(),
                comment.getUser().getId(),
                comment.getAppointment().getId(),
                comment.getUserText(),
                comment.getCreated());
    }
}
