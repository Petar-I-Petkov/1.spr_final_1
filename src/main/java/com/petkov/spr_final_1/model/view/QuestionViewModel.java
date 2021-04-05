package com.petkov.spr_final_1.model.view;

import com.petkov.spr_final_1.model.entity.document.ArticleEntity;
import com.petkov.spr_final_1.model.entity.test.TestEntity;

import java.util.List;

public class QuestionViewModel {

    private String id;
    private String question;
    private String fullReferencePath;
    private String correctAnswer;
    private ArticleViewModel article;

    public QuestionViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFullReferencePath() {
        return fullReferencePath;
    }

    public void setFullReferencePath(String fullReferencePath) {
        this.fullReferencePath = fullReferencePath;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArticleViewModel getArticle() {
        return article;
    }

    public void setArticle(ArticleViewModel article) {
        this.article = article;
    }
}
