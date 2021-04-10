package com.petkov.spr_final_1.model.entity.test;

import com.petkov.spr_final_1.model.entity.UserEntity;
import com.petkov.spr_final_1.model.view.ActiveQuestionViewModel;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActiveTestEntity {

    private String id;
    private String name;
    private LocalDate dueDate;
    private List<ActiveQuestionViewModel> questionEntities;
    private List<SubmittedQuestionEntity> submittedQuestionEntities;

    public ActiveTestEntity() {
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

    public List<SubmittedQuestionEntity> getSubmittedQuestionEntities() {
        return submittedQuestionEntities;
    }

    public void setSubmittedQuestionEntities(List<SubmittedQuestionEntity> submittedQuestionEntities) {
        this.submittedQuestionEntities = submittedQuestionEntities;
    }
}
