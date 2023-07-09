package com.example.quiz.dto;

import lombok.*;


@AllArgsConstructor
@Getter
public final class ResponseDto<T> {
    private final String code;
    private final String description;
    private final T result;

    public static <T> ResponseDto<T> ok(T result) {
        return new ResponseDto<>("ok", null, result);
    }

    public static <T> ResponseDto<T> badRequest(String description) {
        return new ResponseDto<>("bad_request", description, null);
    }
}
