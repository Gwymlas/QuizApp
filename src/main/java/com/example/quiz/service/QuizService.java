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
        var question = repository.findById(answer.getId());
        return question.map(value -> CheckedAnswerDto.builder()
                .id(value.getId())
                .isCorrect(value.getAnswer().equals(answer.getAnswer()))
                .answer(value.getAnswer())
                .build()).orElse(null);
    }
}
