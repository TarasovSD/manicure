package ru.manicure.exceptions;

public class UsersIdsMatchException extends RuntimeException {
    public UsersIdsMatchException(String message) {
        super(message);
    }
}
