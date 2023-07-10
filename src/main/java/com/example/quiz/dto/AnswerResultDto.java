package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnswerResultDto {
    private Long questionId;
    private Boolean correctAnswer;
}
