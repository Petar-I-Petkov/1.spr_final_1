package com.petkov.spr_final_1.model.view;

import com.petkov.spr_final_1.model.entity.TestEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.ArticleEntity;

import java.util.List;

public class ActiveQuestionViewModel {

    private String id;
    private String name;
    private String question;
    private List<String> answers;
    private ArticleViewModel articleViewModel;
    private String givenAnswer;

    public ActiveQuestionViewModel() {
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public ArticleViewModel getArticleViewModel() {
        return articleViewModel;
    }

    public void setArticleViewModel(ArticleViewModel articleViewModel) {
        this.articleViewModel = articleViewModel;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }
}