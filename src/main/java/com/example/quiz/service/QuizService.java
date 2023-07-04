package com.example.quiz.service;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.dto.CheckedAnswerDto;
import com.example.quiz.entity.Question;
import com.example.quiz.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuestionRepository repository;
    public Question getRandomQuestion() {
        return repository.randomQuestion();
    }

    public CheckedAnswerDto checkAnswer(AnswerDto answer) {
        return null;
    }
}
