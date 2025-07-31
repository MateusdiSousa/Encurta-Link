package com.mateus.encurta_link.infraestrutura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mateus.encurta_link.exceptions.InvalidCredentialsException;
import com.mateus.encurta_link.exceptions.ShortLinkConflictException;
import com.mateus.encurta_link.exceptions.ShortLinkNotFoundException;
import com.mateus.encurta_link.exceptions.UserAlreadyExistException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ShortLinkNotFoundException.class)
    private ResponseEntity<String> shortLinkNotFoundHandler(ShortLinkNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Short link not found.");
    }

    @ExceptionHandler(value = ShortLinkConflictException.class)
    private ResponseEntity<String> shortLinkConflictHandler(ShortLinkConflictException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Short link already exist.");
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    private ResponseEntity<String> invalidCredentialHandler(InvalidCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password invalid.");
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    private ResponseEntity<String> userAlreadyExistHandler(UserAlreadyExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

}
