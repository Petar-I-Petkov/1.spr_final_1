package com.petkov.spr_final_1.model.view;

import com.petkov.spr_final_1.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActiveTestViewModel {

    private String id;
    private String name;
    private LocalDate dueDate;
    private List<ActiveQuestionViewModel> questionEntities;
    private Map<Integer, String> correctAnswerMatrix = new LinkedHashMap<>();
    private Map<Integer, String> givenAnswerMatrix = new LinkedHashMap<>();


    public ActiveTestViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public List<ActiveQuestionViewModel> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(List<ActiveQuestionViewModel> questionEntities) {
        this.questionEntities = questionEntities;
    }

    public Map<Integer, String> getCorrectAnswerMatrix() {
        return correctAnswerMatrix;
    }

    public void setCorrectAnswerMatrix(Map<Integer, String> correctAnswerMatrix) {
        this.correctAnswerMatrix = correctAnswerMatrix;
    }

    public Map<Integer, String> getGivenAnswerMatrix() {
        return givenAnswerMatrix;
    }

    public void setGivenAnswerMatrix(Map<Integer, String> givenAnswerMatrix) {
        this.givenAnswerMatrix = givenAnswerMatrix;
    }
}
