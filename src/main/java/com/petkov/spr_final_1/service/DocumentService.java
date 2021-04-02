package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.document.DocumentEntity;
import com.petkov.spr_final_1.model.service.document.DocumentServiceModel;
import com.petkov.spr_final_1.model.view.DocumentViewModel;

import java.util.List;

public interface DocumentService {

    boolean documentExists(String documentName);

    void seedDocumentToDb(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findDocumentByName(String documentRef);

    List<DocumentViewModel> getAllDocumentsSortedAlphabeticallyDesc();

}
