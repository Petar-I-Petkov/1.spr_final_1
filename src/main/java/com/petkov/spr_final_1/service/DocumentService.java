package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.DocumentServiceModel;

public interface DocumentService {

    boolean documentExists(String documentName);

    void seedDocumentToDb(DocumentServiceModel documentServiceModel);
}
