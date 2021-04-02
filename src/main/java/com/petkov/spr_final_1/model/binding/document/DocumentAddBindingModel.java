package com.petkov.spr_final_1.model.binding.document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class DocumentAddBindingModel {

    private String documentName;


    public DocumentAddBindingModel() {
    }

    @NotBlank(message = "Cannot be empty. ")
    @Size(min = 3, message = "Document name must be at least 3 characters. ")
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

}
