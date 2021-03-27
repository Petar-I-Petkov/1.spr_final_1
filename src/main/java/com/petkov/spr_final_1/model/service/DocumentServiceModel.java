package com.petkov.spr_final_1.model.service;

import com.petkov.spr_final_1.model.entity.documentEntities.ParagraphEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DocumentServiceModel {

    private String documentName;
    private List<ParagraphEntity> paragraphs = new ArrayList<>();

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

    public List<ParagraphEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ParagraphEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
