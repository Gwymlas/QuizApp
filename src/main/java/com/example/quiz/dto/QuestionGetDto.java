package com.example.quiz.dto;

import com.example.quiz.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class QuestionGetDto {
    private final Long id;
    private final String question;
    private final Category category;
    private final Integer difficulty;
}
