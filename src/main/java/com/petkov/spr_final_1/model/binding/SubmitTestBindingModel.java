package com.petkov.spr_final_1.model.binding;

import java.util.LinkedHashMap;
import java.util.Map;

public class SubmitTestBindingModel {

    private String id;
    private Map<Integer, String> correctAnswerMatrix = new LinkedHashMap<>();
    private Map<Integer, String> givenAnswerMatrix = new LinkedHashMap<>();

    public SubmitTestBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
