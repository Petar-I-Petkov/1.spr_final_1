package com.petkov.spr_final_1.model.view;

import com.petkov.spr_final_1.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;

public class ActiveTestViewModel {

    private String id;
    private String name;
    private LocalDate dueDate;
    private List<ActiveQuestionViewModel> questionEntities;

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
}
