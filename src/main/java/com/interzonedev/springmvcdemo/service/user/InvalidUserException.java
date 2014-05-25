package com.interzonedev.springmvcdemo.service.user;

public class InvalidUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(Throwable t) {
        super(t);
    }

    public InvalidUserException(String message, Throwable t) {
        super(message, t);
    }

}
