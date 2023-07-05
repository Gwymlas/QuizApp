package com.example.quiz.repository;

import com.example.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(nativeQuery = true, value = "select * from question\n" +
            "where id >= (select ceil((rand() * count(*))) from question)\n" +
            "limit 1;")
    Question randomQuestion();
}
