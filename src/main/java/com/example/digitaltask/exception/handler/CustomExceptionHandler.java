package com.example.digitaltask.exception.handler;

import com.example.digitaltask.exception.*;
import com.example.digitaltask.model.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(value = UserAlreadyRegisteredException.class)
    public ExceptionDto handleUserAlreadyRegisteredException(UserAlreadyRegisteredException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ExceptionDto handleUserNotFoundException(UserNotFoundException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ExceptionDto handleIncorrectPassword(IncorrectPasswordException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = OldAndNewPasswordNotSameException.class)
    public ExceptionDto handleOldAndNewPassword(OldAndNewPasswordNotSameException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = PasswordNotMatchesException.class)
    public ExceptionDto handlePasswordNotMatch(PasswordNotMatchesException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
    @ExceptionHandler(value = ItemNotFoundException.class)
    public ExceptionDto handleIteNotFound(ItemNotFoundException exception) {
        log.error(exception.getMessage());
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
