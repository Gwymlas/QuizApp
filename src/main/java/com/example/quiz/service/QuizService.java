package com.example.quiz.service;

import com.example.quiz.dto.*;
import com.example.quiz.entity.Category;
import com.example.quiz.entity.Question;
import com.example.quiz.exception.NotFoundException;
import com.example.quiz.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuestionRepository repository;

    private Map<String, List<Question>> questionMap;

    private Map<String, List<AnswerResultDto>> scoreMap;

    @Bean
    public Map<String, List<Question>> createQuestionMap() {
        return new HashMap<String, List<Question>>();
    }

    @Bean
    public Map<String, List<AnswerResultDto>> createScoreMap() {
        return new HashMap<String, List<AnswerResultDto>>();
    }

    public Question getRandomQuestion() {
        return repository.randomQuestion();
    }

    public CheckedAnswerDto checkAnswer(AnswerDto answer) {

        var question = repository.findById(answer.getId())
                .orElseThrow(() -> new NotFoundException("Не существует вопроса с ID=" + answer.getId()));

        return new CheckedAnswerDto(answer.getId(), answer.getAnswer().equals(question.getAnswer()), question.getAnswer());
    }

    public CheckedAnswerDto checkAnswer(String id, Integer index, AnswerDto answer) {

        List<Question> questionList = questionMap.get(id);
        if (questionList == null) throw new NotFoundException("Не существует Game ID=" + id);
        if (index >= questionList.size() || index < 0) throw new NotFoundException("Выход за границы");

        var question = questionList.get(index);

        Pair<Long, Boolean> pairQuestionId = Pair.of(answer.getId(), answer.getAnswer().equals(question.getAnswer()));
        AnswerResultDto answerResultDto = new AnswerResultDto(answer.getId(), answer.getAnswer().equals(question.getAnswer()));
        if (scoreMap.get(id) == null) {
            List<AnswerResultDto> result = new ArrayList<>();
            scoreMap.put(id, result);
        }
        scoreMap.get(id).add(answerResultDto);

        return new CheckedAnswerDto(answer.getId(), answer.getAnswer().equals(question.getAnswer()), question.getAnswer());

    }

    public CreateGameResponseDto createGame(CreateGameDto createGameDto) {
        if (createGameDto.getCount() <= 0) throw new RuntimeException("Невалидный аргумент");
        long repositoryCount = repository.count();
        if (repositoryCount < createGameDto.getCount()) throw new RuntimeException("В бд нет столько вопросов");

        String id = UUID.randomUUID().toString();

        if (createGameDto.getCategories() == null) {
            questionMap.put(id, repository.getQuestions(createGameDto.getCount(),
                    createGameDto.getMinDifficulty(), createGameDto.getMaxDifficulty()));
        } else if (createGameDto.getCategories().isEmpty()) {
            questionMap.put(id, repository.getQuestions(createGameDto.getCount(),
                    createGameDto.getMinDifficulty(), createGameDto.getMaxDifficulty()));
        }
        else {
            List<Long> categories_id = new ArrayList<Long>(createGameDto.getCategories().stream().map(Category::getId).toList());
            questionMap.put(id, repository.getQuestions(createGameDto.getCount(),
                    createGameDto.getMinDifficulty(), createGameDto.getMaxDifficulty(), categories_id));
        }
        return new CreateGameResponseDto(id, questionMap.get(id).size());
    }

    public Question getQuestionByGameId(String id, Integer index) {
        List<Question> questionList = questionMap.get(id);
        if (questionList == null) throw new NotFoundException("Не существует Game ID=" + id);
        if (index >= questionList.size() || index < 0) throw new NotFoundException("Не существует вопроса по индексу=" + index);

        return questionMap.get(id).get(index);
    }

    public List<AnswerResultDto> finishGame(String id) {
        List<Question> questionList = questionMap.get(id);
        if (questionList == null) throw new NotFoundException("Не существует Game ID=" + id);

        var result = scoreMap.get(id);
        questionMap.remove(id);
        scoreMap.remove(id);

        return result;
    }
}
