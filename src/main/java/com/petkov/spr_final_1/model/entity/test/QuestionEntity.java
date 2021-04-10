package com.petkov.spr_final_1.model.entity.test;

import com.petkov.spr_final_1.model.entity.BaseEntity;
import com.petkov.spr_final_1.model.entity.document.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {

    private String name;
    private String question;
    private String fullReferencePath;
    private String correctAnswer;
    private String altAnswer1;
    private String altAnswer2;
    private String altAnswer3;
    private String altAnswer4;

    private List<TestEntity> tests;

    private ArticleEntity article;

    private ATAChapterEntity chapter;
    private ATASubChapterEntity ataSubChapter;

    private DocumentEntity document;
    private DocumentSubchapterEntity documentSubchapter;

    public QuestionEntity() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "question", unique = true, nullable = false, columnDefinition = "TEXT")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Column(name = "full_reference_path", unique = false, nullable = true)
    public String getFullReferencePath() {
        return fullReferencePath;
    }

    public void setFullReferencePath(String fullReferencePath) {
        this.fullReferencePath = fullReferencePath;
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


    @ManyToOne(cascade = CascadeType.ALL)// - todo - refactor this (cascade = CascadeType.ALL)
    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    @ManyToOne
    @JoinColumn(name = "ata_chapter", referencedColumnName = "id")
    public ATAChapterEntity getChapter() {
        return chapter;
    }

    public void setChapter(ATAChapterEntity chapter) {
        this.chapter = chapter;
    }

    @ManyToOne
    @JoinColumn(name = "ata_subchapter", referencedColumnName = "id")
    public ATASubChapterEntity getAtaSubChapter() {
        return ataSubChapter;
    }

    public void setAtaSubChapter(ATASubChapterEntity ataSubChapter) {
        this.ataSubChapter = ataSubChapter;
    }

    @ManyToOne
    @JoinColumn(name = "document", referencedColumnName = "id")
    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    @ManyToOne
    @JoinColumn(name = "document_subchapter", referencedColumnName = "id")
    public DocumentSubchapterEntity getDocumentSubchapter() {
        return documentSubchapter;
    }

    public void setDocumentSubchapter(DocumentSubchapterEntity documentSubchapter) {
        this.documentSubchapter = documentSubchapter;
    }
}
