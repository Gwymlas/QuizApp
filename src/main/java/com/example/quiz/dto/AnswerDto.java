package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class AnswerDto {
    private final Long id;
    private final String answer;
}
