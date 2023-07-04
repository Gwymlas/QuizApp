package com.example.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckedAnswerDto {
    private Long id;
    private Boolean isCorrect;
    private String answer;
}
