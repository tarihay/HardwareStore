package ru.nsu.ccfit.db.hardwarestore.exceptions;

public class NoSuchUserFoundException extends RuntimeException {
    public NoSuchUserFoundException(String message) {
        super(message);
    }
}
