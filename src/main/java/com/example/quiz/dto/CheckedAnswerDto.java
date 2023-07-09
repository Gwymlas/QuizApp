package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class CheckedAnswerDto {
    private final Long id;
    private final Boolean isCorrect;
    private final String answer;
}
