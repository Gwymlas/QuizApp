package com.example.quiz.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Getter
public class ResponseError {
    private final HttpStatus httpStatus;
    private final String message;

    private final LocalDate time = LocalDate.now();

    public ResponseError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
