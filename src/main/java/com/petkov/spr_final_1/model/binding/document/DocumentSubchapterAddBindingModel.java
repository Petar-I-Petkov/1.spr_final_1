package com.petkov.spr_final_1.model.binding.document;

import com.petkov.spr_final_1.model.entity.document.DocumentEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DocumentSubchapterAddBindingModel {

    private String docSubchapterName;
    private String documentRef;

    public DocumentSubchapterAddBindingModel() {
    }

    @NotBlank(message = "Cannot be empty. ")
    @Size(min = 3, message = "Subchapter name must be at least 3 characters. ")
    public String getDocSubchapterName() {
        return docSubchapterName;
    }

    public void setDocSubchapterName(String docSubchapterName) {
        this.docSubchapterName = docSubchapterName;
    }

    @NotBlank(message = "Document required.")
    public String getDocumentRef() {
        return documentRef;
    }

    public void setDocumentRef(String documentRef) {
        this.documentRef = documentRef;
    }
}
