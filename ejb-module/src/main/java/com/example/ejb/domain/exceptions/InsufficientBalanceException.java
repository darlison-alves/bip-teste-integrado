package com.example.ejb.domain.exceptions;

public class InsufficientBalanceException extends BaseException {
    public InsufficientBalanceException() {
        super(400, "Insufficient Balance");
    }
}
