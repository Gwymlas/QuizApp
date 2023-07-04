package com.example.quiz.repository;

import com.example.quiz.entity.Category;
import com.example.quiz.entity.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository {

    public Question randomQuestion() {
        var category = new Category(1L, "");
        return new Question(1L, "", "", category, 100);
    }
}
