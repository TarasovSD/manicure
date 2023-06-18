package ru.manicure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.manicure.model.dto.comment.CommentDto;
import ru.manicure.model.dto.comment.CommentFullDto;
import ru.manicure.service.CommentService;

import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
@Slf4j
@RequiredArgsConstructor
@Validated
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all/{appointmentId}")
    public List<CommentFullDto> getAllByAppointmentId(@PositiveOrZero @PathVariable Long appointmentId) {
        log.info("A request to get all comments on an appointment with id {} has been sent to the commentService",
                appointmentId);
        return commentService.getAllByAppointmentId(appointmentId);
    }

    @PostMapping()
    public CommentFullDto create(@RequestBody CommentDto commentDto) {
        log.info("A request to create comment has been sent to the commentService");
        return commentService.create(commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void remove(@PositiveOrZero @PathVariable Long commentId) {
        log.info("A request to delete comment with id {} has been sent to the commentService", commentId);
        commentService.remove(commentId);
    }
}
