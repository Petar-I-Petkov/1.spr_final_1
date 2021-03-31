package com.petkov.spr_final_1.model.entity;

import com.petkov.spr_final_1.model.entity.documentEntities.ArticleEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity{

    private String name;
    private String question;
    private String correctAnswer;
    private String altAnswer1;
    private String altAnswer2;
    private String altAnswer3;
    private String altAnswer4;
    private List<TestEntity> tests;
    private ArticleEntity article;

    public QuestionEntity() {
    }

    @Column(name = "question", unique = false, nullable = false)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "correct_answer", unique = false, nullable = false)
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Column(name = "alt_answer_1", unique = false, nullable = false)
    public String getAltAnswer1() {
        return altAnswer1;
    }

    public void setAltAnswer1(String altAnswer1) {
        this.altAnswer1 = altAnswer1;
    }

    @Column(name = "alt_answer_2", unique = false, nullable = false)
    public String getAltAnswer2() {
        return altAnswer2;
    }

    public void setAltAnswer2(String altAnswer2) {
        this.altAnswer2 = altAnswer2;
    }

    @Column(name = "alt_answer_3", unique = false, nullable = true)
    public String getAltAnswer3() {
        return altAnswer3;
    }

    public void setAltAnswer3(String altAnswer3) {
        this.altAnswer3 = altAnswer3;
    }

    @Column(name = "alt_answer_4", unique = false, nullable = true)
    public String getAltAnswer4() {
        return altAnswer4;
    }

    public void setAltAnswer4(String altAnswer4) {
        this.altAnswer4 = altAnswer4;
    }

    @ManyToMany
    @JoinTable(
            name = "tests_questions",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id")
    )
    public List<TestEntity> getTests() {
        return tests;
    }

    public void setTests(List<TestEntity> tests) {
        this.tests = tests;
    }


    @ManyToOne(cascade = CascadeType.ALL)
    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }
}
