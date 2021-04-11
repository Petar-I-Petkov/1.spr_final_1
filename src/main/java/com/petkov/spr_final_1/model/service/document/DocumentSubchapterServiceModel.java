package com.petkov.spr_final_1.model.service.document;

import com.google.gson.annotations.Expose;
import com.petkov.spr_final_1.model.service.BaseServiceModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DocumentSubchapterServiceModel extends BaseServiceModel {

    @Expose
    private String docSubchapterName;
    @Expose
    private String documentRef;

    public DocumentSubchapterServiceModel() {
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
