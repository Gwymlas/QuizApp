package com.example.quiz.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnswerDto {
    private Long id;
    private String answer;
}
