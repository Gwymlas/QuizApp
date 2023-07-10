package com.example.quiz.repository;

import com.example.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(nativeQuery = true, value = "select * from question\n" +
            "where id >= (select ceil((rand() * count(*))) from question)\n" +
            "limit 1;")
    Question randomQuestion();

    @Query(nativeQuery = true, value = "select * from question order by rand() limit :count")
    List<Question> getQuestions(Integer count);

    @Query(nativeQuery = true, value = "select * from question\n" +
            "where difficulty <= :MaxDifficulty and difficulty >= :minDifficulty \n" +
            "order by rand() limit :count")
    List<Question> getQuestions(Integer count, Integer minDifficulty, Integer MaxDifficulty);

    @Query(nativeQuery = true, value = "select * from question\n" +
            "where difficulty <= :MaxDifficulty and difficulty >= :minDifficulty and category_id in :categories_id\n" +
            "order by rand() limit :count")
    List<Question> getQuestions(Integer count, Integer minDifficulty, Integer MaxDifficulty, List<Long> categories_id);


}
