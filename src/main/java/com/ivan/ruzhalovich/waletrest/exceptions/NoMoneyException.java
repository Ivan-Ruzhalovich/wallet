package com.ivan.ruzhalovich.waletrest.exceptions;

public class NoMoneyException extends RuntimeException{
    public NoMoneyException(String message) {
        super(message);
    }
}
