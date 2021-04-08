package com.petkov.spr_final_1.model.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DocumentSubchapterViewModel {

    private String id;
    private String docSubchapterName;
    private String documentRef;

    public DocumentSubchapterViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDocSubchapterName() {
        return docSubchapterName;
    }

    public void setDocSubchapterName(String docSubchapterName) {
        this.docSubchapterName = docSubchapterName;
    }


    public String getDocumentRef() {
        return documentRef;
    }

    public void setDocumentRef(String documentRef) {
        this.documentRef = documentRef;
    }
}
