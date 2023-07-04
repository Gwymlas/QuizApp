package com.example.quiz.controllers;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.dto.CheckedAnswerDto;
import com.example.quiz.dto.QuestionGetDto;
import com.example.quiz.service.QuizService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuestionController {

    private final QuizService quizService;
    @GetMapping("random")
    public QuestionGetDto getRandomQuestion() {
        var question = quizService.getRandomQuestion();
        return QuestionGetDto.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .category(question.getCategory())
                .difficulty(question.getDifficulty())
                .build();
    }

    @PostMapping("check")
    public CheckedAnswerDto checkAnswer(@RequestBody AnswerDto answer) {
        return quizService.checkAnswer(answer);
    }
}
