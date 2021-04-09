package com.petkov.spr_final_1.model.service.test;

import com.petkov.spr_final_1.model.entity.UserEntity;
import com.petkov.spr_final_1.model.entity.enumeration.TestStatusEnum;
import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import com.petkov.spr_final_1.model.service.BaseServiceModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class TestServiceModel extends BaseServiceModel {


    private String name;
    private LocalDate dateCreated;
    private LocalDate dueDate;
    private UserEntity createdBy;
    private TestStatusEnum testStatusEnum;
    private List<String> questionIds;

    @NotBlank(message = "Test name cannot be empty.")
    @Size(min = 3, message = "Test name length min 3 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @NotNull(message = "Test due date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Test due date cannot be in the past.")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    @Enumerated(EnumType.STRING)
    public TestStatusEnum getTestStatusEnum() {
        return testStatusEnum;
    }

    public void setTestStatusEnum(TestStatusEnum testStatusEnum) {
        this.testStatusEnum = testStatusEnum;
    }

    @NotEmpty(message = "There must be at least one question in the test.")
    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }



}
