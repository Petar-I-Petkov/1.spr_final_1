package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ATAChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.ATASubChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.DocumentEntity;
import com.petkov.spr_final_1.model.service.ATASubChapterServiceModel;
import com.petkov.spr_final_1.model.service.DocumentServiceModel;
import com.petkov.spr_final_1.repository.DocumentRepository;
import com.petkov.spr_final_1.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final ModelMapper modelMapper;
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(ModelMapper modelMapper, DocumentRepository documentRepository) {
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
    }


    @Override
    public boolean documentExists(String documentName) {
        return this.documentRepository.findByDocumentName(documentName.toLowerCase().trim()).isPresent();
    }

    @Override
    public void seedDocumentToDb(DocumentServiceModel documentServiceModel) {

        DocumentEntity documentEntity = this.modelMapper.map(documentServiceModel, DocumentEntity.class);

        documentEntity.setDocumentName(documentEntity.getDocumentName().toLowerCase().trim());

        this.documentRepository.saveAndFlush(documentEntity);

    }


}
