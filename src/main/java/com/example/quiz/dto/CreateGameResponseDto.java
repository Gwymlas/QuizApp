package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public final class CreateGameResponseDto {
    @NonNull
    private final String id;
    @NonNull
    private final Integer count;
}
