package com.petkov.spr_final_1.model.service.test;

import com.petkov.spr_final_1.model.service.BaseServiceModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuestionServiceModel extends BaseServiceModel {

    private String name;
    private String question;
    private String fullReferencePath;

    private String correctAnswer;
    private String altAnswer1;
    private String altAnswer2;
    private String altAnswer3;
    private String altAnswer4;
    private String articleRef;

    public QuestionServiceModel() {
    }

    @NotNull(message = "Question title is required.")
    @Size(min = 3, message = "Question title should be min 3 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Question text is required.")
    @Size(min = 50, message = "Question text should be min 50 characters.")
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

    @NotBlank(message = "Correct Answer is required.")
    @Size(min = 2, message = "Correct Answer should be min 2 characters.")
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @NotBlank(message = "Alternate answer 1 is required.")
    @Size(min = 2, message = "Alternate answer 1 should be min 2 characters.")
    public String getAltAnswer1() {
        return altAnswer1;
    }

    public void setAltAnswer1(String altAnswer1) {
        this.altAnswer1 = altAnswer1;
    }

    @NotBlank(message = "Alternate answer 2 is required.")
    @Size(min = 2, message = "Alternate answer 2 should be min 2 characters.")
    public String getAltAnswer2() {
        return altAnswer2;
    }

    public void setAltAnswer2(String altAnswer2) {
        this.altAnswer2 = altAnswer2;
    }

    public String getAltAnswer3() {
        return altAnswer3;
    }

    public void setAltAnswer3(String altAnswer3) {
        this.altAnswer3 = altAnswer3;
    }

    public String getAltAnswer4() {
        return altAnswer4;
    }

    public void setAltAnswer4(String altAnswer4) {
        this.altAnswer4 = altAnswer4;
    }

    public String getArticleRef() {
        return articleRef;
    }

    public void setArticleRef(String articleRef) {
        this.articleRef = articleRef;
    }
}
