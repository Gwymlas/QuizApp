package com.example.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "question", nullable = false)
    private String question;

    @NonNull
    @Column(name = "answer", nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn
    private Category category;

    @NonNull
    @Column(name = "difficulty", nullable = false)
    private Integer difficulty;
}

