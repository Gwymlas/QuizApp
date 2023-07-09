package com.example.quiz.controllers;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.dto.CheckedAnswerDto;
import com.example.quiz.dto.QuestionGetDto;
import com.example.quiz.dto.ResponseDto;
import com.example.quiz.service.QuizService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("question")
public class QuestionController {

    private final QuizService quizService;
    @GetMapping("random")
    public ResponseDto<QuestionGetDto> getRandomQuestion() {
        var question = quizService.getRandomQuestion();
        var questionGetDto = new QuestionGetDto(question.getId(),
                                                question.getQuestion(),
                                                question.getCategory(),
                                                question.getDifficulty());
        return ResponseDto.ok(questionGetDto);
    }

    @PostMapping("check")
    public ResponseDto<CheckedAnswerDto> checkAnswer(@RequestBody AnswerDto answer) {
        var checkedAnswer = quizService.checkAnswer(answer);
        return ResponseDto.ok(checkedAnswer);
    }
}
