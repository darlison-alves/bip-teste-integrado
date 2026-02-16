package com.example.ejb.domain.exceptions;

public class NotFountException extends BaseException {
    public NotFountException(String message) {
        super(404, message);
    }
}
