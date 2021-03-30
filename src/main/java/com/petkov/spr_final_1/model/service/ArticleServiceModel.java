package com.petkov.spr_final_1.model.service;

import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.DocumentEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.SubChapterEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ArticleServiceModel extends BaseServiceModel {

    private String title;
    private String articleText;
    private MultipartFile image;
    private String fullReferencePath;

    private String chapterRef;
    private String ataSubChapterRef;

    private String documentRef;
    private String documentRevision;
    private LocalDate documentDate;


    public ArticleServiceModel() {
    }

    @NotNull(message = "Article title is required.")
    @Size(min = 3, message = "Article title should be min 3 characters.")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @NotNull(message = "Article text is required.")
    @Size(min = 200, message = "Article text should be min 200 characters.")
    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }


    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }
}
