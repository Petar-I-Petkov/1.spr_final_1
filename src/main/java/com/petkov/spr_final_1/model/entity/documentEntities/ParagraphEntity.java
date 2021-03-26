package com.petkov.spr_final_1.model.entity.documentEntities;


import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "paragraphs")
public class ParagraphEntity extends BaseEntity {

    private String title;
    private String documentRevision;
    private LocalDateTime documentDate;
    private String description;
    // todo - ParagraphEntity - add picture field
    private ATASubChapterEntity ataSubChapter;
    private DocumentEntity document;

    public ParagraphEntity() {
    }

    @Column(name = "title", unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "revision", unique = false, nullable = true)
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

    @Column(name = "description", unique = false, nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "ata_sub_chapter", referencedColumnName = "id")
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
}
