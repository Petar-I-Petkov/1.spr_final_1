package com.petkov.spr_final_1.model.entity.test;

import com.petkov.spr_final_1.model.entity.BaseEntity;
import com.petkov.spr_final_1.model.entity.UserEntity;
import com.petkov.spr_final_1.model.entity.enumeration.TestStatusEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tests")
public class TestEntity extends BaseEntity {

    private String name;
    private LocalDate dateCreated;
    private LocalDate dueDate;
    private UserEntity createdBy;
    private TestStatusEnum testStatusEnum;
    private List<QuestionEntity> questionEntities;

    public TestEntity() {
    }

    public TestEntity(String name) {
        this.name = name;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "date_created", unique = false, nullable = false)
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "due_date", unique = false, nullable = false)
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @ManyToOne
    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    @Enumerated(EnumType.STRING)
    public TestStatusEnum getTestStatus() {
        return testStatusEnum;
    }

    public void setTestStatus(TestStatusEnum testStatusEnum) {
        this.testStatusEnum = testStatusEnum;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tests_questions",
            joinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id")
    )
    public List<QuestionEntity> getQuestions() {
        return questionEntities;
    }

    public void setQuestions(List<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }
}