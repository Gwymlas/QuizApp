package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public final class AnswerDto {
    @NonNull
    private final Long id;
    @NonNull
    private final String answer;
}
