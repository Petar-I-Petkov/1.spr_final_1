package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.document.DocumentSubchapterServiceModel;

public interface DocumentSubChapterService {

    boolean subChapterExistsInDocument(String documentRef, String docSubchapterName);

    void seedDocumentSubchapterToDb(DocumentSubchapterServiceModel documentSubchapterServiceModel);
}
