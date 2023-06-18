package ru.manicure.exceptions;

public class UserIsNotMasterException extends RuntimeException {
    public UserIsNotMasterException(String message) {
        super(message);
    }
}
