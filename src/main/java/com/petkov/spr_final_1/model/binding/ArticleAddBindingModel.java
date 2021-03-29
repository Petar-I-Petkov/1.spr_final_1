package com.petkov.spr_final_1.model.binding;

import java.time.LocalDateTime;

public class ArticleAddBindingModel {

    private String title;
    private String articleText;
    private String imageUrl;
    private String fullReferencePath;

    private String chapterRef;
    private String ataSubChapterRef;

    private String documentRef;
    private String documentRevision;
    private LocalDateTime documentDate;

    public ArticleAddBindingModel() {
    }

    //todo - ArticleBindingModel add validations

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullReferencePath() {
        return fullReferencePath;
    }

    public void setFullReferencePath(String fullReferencePath) {
        this.fullReferencePath = fullReferencePath;
    }

    public String getChapterRef() {
        return chapterRef;
    }

    public void setChapterRef(String chapterRef) {
        this.chapterRef = chapterRef;
    }

    public String getAtaSubChapterRef() {
        return ataSubChapterRef;
    }

    public void setAtaSubChapterRef(String ataSubChapterRef) {
        this.ataSubChapterRef = ataSubChapterRef;
    }

    public String getDocumentRef() {
        return documentRef;
    }

    public void setDocumentRef(String documentRef) {
        this.documentRef = documentRef;
    }

    public String getDocumentRevision() {
        return documentRevision;
    }

    public void setDocumentRevision(String documentRevision) {
        this.documentRevision = documentRevision;
    }

    public LocalDateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }
}
