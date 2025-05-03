package com.filmotokio.exception;

public class ReviewExistException extends RuntimeException {
    public ReviewExistException(String message) {
        super(message);
    }
}
