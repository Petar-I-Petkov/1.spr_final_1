package com.petkov.spr_final_1.model.service.document;

import com.petkov.spr_final_1.model.entity.BaseEntity;
import com.petkov.spr_final_1.model.entity.document.ArticleEntity;
import com.petkov.spr_final_1.model.service.BaseServiceModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DocumentServiceModel extends BaseServiceModel {

    private String documentName;
    private List<ArticleEntity> paragraphs = new ArrayList<>();

    public DocumentServiceModel() {
    }

    @NotBlank(message = "Cannot be empty. ")
    @Size(min = 3, message = "Document name must be at least 3 characters. ")
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public List<ArticleEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ArticleEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
