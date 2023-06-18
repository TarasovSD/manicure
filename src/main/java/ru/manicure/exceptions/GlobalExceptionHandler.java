package ru.manicure.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(final UserNotFoundException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProcedureNotFoundException.class)
    public ResponseEntity<String> handleProcedureNotFoundException(final ProcedureNotFoundException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsersIdsMatchException.class)
    public ResponseEntity<String> handleUsersIdsMatchException(final UsersIdsMatchException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = IntersectionOfAppointmentsException.class)
    public ResponseEntity<String> handleIntersectionOfAppointmentsException(final IntersectionOfAppointmentsException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UserIsNotMasterException.class)
    public ResponseEntity<String> handleUserIsNotMasterException(final UserIsNotMasterException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UnknownTemporaryStatusException.class)
    public ResponseEntity<String> handleUnknownTemporaryStatusException(final UnknownTemporaryStatusException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = AppointmentNotFoundException.class)
    public ResponseEntity<String> handleAppointmentNotFoundException(final AppointmentNotFoundException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UnknownStatusException.class)
    public ResponseEntity<String> handleUnknownStatusException(final UnknownStatusException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = CommentNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFoundException(final CommentNotFoundException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(final ConstraintViolationException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<String> handleHibernateConstraintViolationException(final org.hibernate.exception.ConstraintViolationException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}