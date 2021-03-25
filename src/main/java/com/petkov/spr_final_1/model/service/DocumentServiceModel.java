package com.petkov.spr_final_1.model.service;

import com.petkov.spr_final_1.model.entity.documentEntities.ParagraphEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class DocumentServiceModel {

    private String name;
    private List<ParagraphEntity> paragraphs = new ArrayList<>();

    public DocumentServiceModel() {
    }

    @NotBlank(message = "Document name cannot be empty.")
    @Size(min = 3, message = "Document name length must be at least 3 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ParagraphEntity> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<ParagraphEntity> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
