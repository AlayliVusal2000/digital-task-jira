package com.example.digitaltask.exception;

import javax.management.RuntimeMBeanException;

public class PasswordNotMatchesException extends RuntimeException {
    public PasswordNotMatchesException(String message) {
        super(message);
    }
}
