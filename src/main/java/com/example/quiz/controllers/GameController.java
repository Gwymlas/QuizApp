package com.example.quiz.controllers;

import com.example.quiz.dto.*;
import com.example.quiz.entity.Question;
import com.example.quiz.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.quiz.dto.ResponseDto.ok;

@RestController
@AllArgsConstructor
@RequestMapping("game")
public class GameController {
    private final QuizService quizService;

    /* Request Body
    {
        "count": 3,
        "minDifficulty": 1,
        "maxDifficulty": 1000,
        "categories": [
            {
                "id": 1,
                "name": "first"
            },
            {
                "id": 2,
                "name": "second"
            }
        ]
    }
     */
    @PostMapping("create")
    public ResponseDto<CreateGameResponseDto> createGame(@RequestBody CreateGameDto createGameDto) {
        var response = quizService.createGame(createGameDto);
        return ok(response);
    }

    @GetMapping("{gameId}/{index}")
    public ResponseDto<QuestionGetDto> getQuestion(@PathVariable String gameId, @PathVariable Integer index) {
        Question question = quizService.getQuestionByGameId(gameId, index);
        QuestionGetDto questionGetDto = new QuestionGetDto(question.getId(), question.getQuestion(),
                question.getCategory(), question.getDifficulty());
        return ok(questionGetDto);
    }

    @PostMapping("{gameId}/{index}/check")
    public ResponseDto<CheckedAnswerDto> checkAnswer(@PathVariable String gameId, @PathVariable Integer index, @RequestBody AnswerDto answer) {
        return ok(quizService.checkAnswer(gameId, index, answer));
    }

    @PostMapping("{gameId}/finish")
    public ResponseDto<List<AnswerResultDto>> finishGame(@PathVariable String gameId) {
         return ok(quizService.finishGame(gameId));
    }
}
