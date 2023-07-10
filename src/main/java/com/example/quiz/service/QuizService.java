package com.example.quiz.service;

import com.example.quiz.dto.AnswerDto;
import com.example.quiz.dto.CheckedAnswerDto;
import com.example.quiz.dto.CreateGameDto;
import com.example.quiz.dto.CreateGameResponseDto;
import com.example.quiz.entity.Category;
import com.example.quiz.entity.Question;
import com.example.quiz.exception.NotFoundException;
import com.example.quiz.repository.QuestionRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuestionRepository repository;

    private Map<String, List<Question>> questionMap;

    @Bean
    public Map<String, List<Question>> createQuestionMap() {
        return new HashMap<String, List<Question>>();
    }
//    public QuizService(QuestionRepository repository) {
//        this.repository = repository;
//        this.questionMap = new HashMap<String, List<Question>>();
//    }

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
        if (index >= questionList.size() || index < 0) throw new NotFoundException("Выход за границы");

        return questionMap.get(id).get(index);
    }
}
