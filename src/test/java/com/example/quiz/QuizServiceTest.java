package com.example.quiz;

import com.example.quiz.entity.Category;
import com.example.quiz.entity.Question;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.service.QuizService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuizService quizService;

    @Test
    void shouldReturnRandomQuestion() {
        List<Question> questions = createQuestions();

        Mockito.when(questionRepository.randomQuestion()).thenReturn(questions.get(0));

        Question result = quizService.getRandomQuestion();

        Assertions.assertNotNull(result);

    }

    private List<Question> createQuestions() {
        Question firstQuestion = new Question();
        Question secondQuestion = new Question();

        Category firstCategory = new Category(1L, "A");
        Category secondCategory = new Category(2L, "B");

        firstQuestion.setId(1L);
        firstQuestion.setQuestion("First");
        firstQuestion.setAnswer("First");
        firstQuestion.setCategory(firstCategory);
        firstQuestion.setDifficulty(100);

        secondQuestion.setId(2L);
        secondQuestion.setQuestion("Second");
        secondQuestion.setAnswer("Second");
        secondQuestion.setCategory(secondCategory);
        secondQuestion.setDifficulty(200);

        return List.of(firstQuestion, secondQuestion);
    }
}
