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
        return ResponseDto.ok(QuestionGetDto.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .category(question.getCategory())
                .difficulty(question.getDifficulty())
                .build());
    }

    @PostMapping("check")
    public ResponseDto<CheckedAnswerDto> checkAnswer(@RequestBody AnswerDto answer) {
        if (answer.getId() == null) {
            return ResponseDto.badRequest("Не введен ID");
        }

        if (answer.getAnswer() == null) {
            return ResponseDto.badRequest("Не введен ответ");
        }

        var checkedAnswer = quizService.checkAnswer(answer);
        if (checkedAnswer == null) return ResponseDto.badRequest("Не существует вопроса с таким ID");
        return ResponseDto.ok(checkedAnswer);
    }
}
