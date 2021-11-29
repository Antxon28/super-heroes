package com.antonio.superhero.common.exception;

import com.antonio.superhero.common.response.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> runTimeException(RuntimeException e) {
        return this.createBodyResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> generalException(Exception e) {
        return this.createBodyResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> statusException(ResponseStatusException e) {
        return this.createBodyResponse(e, e.getStatus());
    }

    private ResponseEntity<ErrorDTO> createBodyResponse(Exception e, HttpStatus httpStatus) {
        return new ResponseEntity<>(ErrorDTO.builder()
                .status(httpStatus.toString())
                .message(e.getMessage())
                .build(), httpStatus);
    }
}
