package ru.manicure.exceptions;

public class UnknownTemporaryStatusException extends RuntimeException {
    public UnknownTemporaryStatusException(String message) {
        super(message);
    }
}
