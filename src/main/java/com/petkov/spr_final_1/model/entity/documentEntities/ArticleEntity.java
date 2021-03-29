package com.petkov.spr_final_1.model.entity.documentEntities;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class ArticleEntity extends BaseEntity {

    private String title;
    private String articleText;
    private String imageUrl;
    private String fullReferencePath;

    private ChapterEntity chapter;
    private SubChapterEntity ataSubChapter;

    private DocumentEntity document;
    private String documentRevision;
    private LocalDateTime documentDate;

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

    @ManyToOne
    @JoinColumn(name = "ata_chapter", referencedColumnName = "id")
    public ChapterEntity getChapter() {
        return chapter;
    }

    public void setChapter(ChapterEntity chapter) {
        this.chapter = chapter;
    }

    @ManyToOne
    @JoinColumn(name = "ata_sub_chapter", referencedColumnName = "id")
    public SubChapterEntity getAtaSubChapter() {
        return ataSubChapter;
    }

    public void setAtaSubChapter(SubChapterEntity ataSubChapter) {
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

    @Column(name = "ducument_revision", unique = false, nullable = true)
    public String getDocumentRevision() {
        return documentRevision;
    }

    public void setDocumentRevision(String documentRevision) {
        this.documentRevision = documentRevision;
    }

    @Column(name = "document_date", unique = false, nullable = true)
    public LocalDateTime getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }

}
