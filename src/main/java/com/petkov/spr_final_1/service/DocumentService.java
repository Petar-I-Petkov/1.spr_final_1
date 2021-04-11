package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.document.DocumentEntity;
import com.petkov.spr_final_1.model.service.document.DocumentServiceModel;
import com.petkov.spr_final_1.model.view.DocumentViewModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface DocumentService {

    void initSeedDocumentsFromJson();

    boolean documentExists(String documentName);

    DocumentServiceModel seedDocumentToDb(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findDocumentByName(String documentName);

    List<DocumentViewModel> findAllDocumentsSortedByNameDesc();

    DocumentServiceModel findDocumentById(String id);

    CompletableFuture<List<DocumentViewModel>> findAllDocumentsSortedByNameDescAsync();

}
