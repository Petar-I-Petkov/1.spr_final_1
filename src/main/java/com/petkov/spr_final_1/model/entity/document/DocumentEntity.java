package com.petkov.spr_final_1.model.entity.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "documents")
public class DocumentEntity extends BaseEntity {

    private String documentName;
    private List<DocumentSubchapterEntity> docSubchapters;
    private List<ArticleEntity> articles;

    public DocumentEntity() {
    }

    @Column(name = "document_name", unique = true, nullable = false)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @OneToMany(mappedBy = "document", targetEntity = DocumentSubchapterEntity.class, cascade = CascadeType.ALL)
    public List<DocumentSubchapterEntity> getDocSubchapters() {
        return docSubchapters;
    }

    public void setDocSubchapters(List<DocumentSubchapterEntity> docSubchapters) {
        this.docSubchapters = docSubchapters;
    }

    @OneToMany(mappedBy = "document", targetEntity = ArticleEntity.class, cascade = CascadeType.ALL)
    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}
