package com.example.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Long id;
    private String question;
    private String answer;
    private Category category;
    private Integer difficulty;
}

