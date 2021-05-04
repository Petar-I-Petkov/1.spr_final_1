package com.petkov.spr_final_1.model.entity.document;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "articles")
@Access(AccessType.PROPERTY)
public class ArticleEntity extends BaseEntity {

    private String title;
    private String articleText;
    private String imageUrl;
    private String fullReferencePath;

    private String documentRevision;
    private LocalDate documentDate;

    public ArticleEntity() {
    }

    @Column(name = "title", unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "article_text", unique = false, nullable = false, columnDefinition = "TEXT")
    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    @Column(name = "image_url", unique = false, nullable = true)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "full_reference_path", unique = false, nullable = true)
    public String getFullReferencePath() {
        return fullReferencePath;
    }

    public void setFullReferencePath(String fullReferencePath) {
        this.fullReferencePath = fullReferencePath;
    }

    @Column(name = "ducument_revision", unique = false, nullable = true)
    public String getDocumentRevision() {
        return documentRevision;
    }

    public void setDocumentRevision(String documentRevision) {
        this.documentRevision = documentRevision;
    }

    @Column(name = "document_date", unique = false, nullable = true)
    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

}
