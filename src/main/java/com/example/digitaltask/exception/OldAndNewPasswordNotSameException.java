package com.example.digitaltask.exception;

public class OldAndNewPasswordNotSameException extends RuntimeException {
    public OldAndNewPasswordNotSameException(String message) {
        super(message);
    }
}
