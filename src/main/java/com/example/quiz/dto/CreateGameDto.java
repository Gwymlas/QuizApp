package com.example.quiz.dto;

import com.example.quiz.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
public final class CreateGameDto {
    @NonNull
    private final Integer count;
    @NonNull
    private final Integer minDifficulty;
    @NonNull
    private final Integer maxDifficulty;
    private final ArrayList<Category> categories;
}
