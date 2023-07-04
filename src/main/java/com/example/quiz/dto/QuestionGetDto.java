package com.example.quiz.dto;

import com.example.quiz.entity.Category;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionGetDto {
    private Long id;
    private String question;
    private Category category;
    private Integer difficulty;
}
