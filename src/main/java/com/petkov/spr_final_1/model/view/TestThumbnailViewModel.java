package com.petkov.spr_final_1.model.view;

import java.time.LocalDate;

public class TestThumbnailViewModel {

    private String id;
    private String name;
    private int numberOfQuestions;

    public TestThumbnailViewModel() {
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

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
