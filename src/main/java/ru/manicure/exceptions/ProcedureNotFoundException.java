package ru.manicure.exceptions;

public class ProcedureNotFoundException extends RuntimeException {
    public ProcedureNotFoundException(String message) {
        super(message);
    }
}
