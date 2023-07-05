package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String code;
    private String description;
    private T result;

    public static <T> ResponseDto<T> ok(T result) {
        return new ResponseDto<>("ok", null, result);
    }

    public static <T> ResponseDto<T> badRequest(String description) {
        return new ResponseDto<>("bad_request", description, null);
    }
}
