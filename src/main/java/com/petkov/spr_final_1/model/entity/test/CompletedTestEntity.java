package com.petkov.spr_final_1.model.entity.test;

import com.petkov.spr_final_1.model.entity.BaseEntity;
import com.petkov.spr_final_1.model.entity.UserEntity;
import com.petkov.spr_final_1.model.view.ActiveQuestionViewModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "completed_tests")
public class CompletedTestEntity extends BaseEntity {

    private String parentTestId;
    private String name;
    private LocalDate dueDate;
    private LocalDateTime timeCompleted;
    private List<SubmittedQuestionEntity> questionEntities;
    private UserEntity userEntity;

    public CompletedTestEntity() {
    }

    //todo - add columns at CompletedTestEntity
    public String getParentTestId() {
        return parentTestId;
    }

    public void setParentTestId(String parentTestId) {
        this.parentTestId = parentTestId;
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

    public LocalDateTime getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(LocalDateTime timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    @OneToMany(
            mappedBy = "completedTestEntity",
            targetEntity = SubmittedQuestionEntity.class,
            cascade = CascadeType.ALL)
    public List<SubmittedQuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(List<SubmittedQuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}