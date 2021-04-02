package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.document.DocumentEntity;
import com.petkov.spr_final_1.model.service.document.DocumentServiceModel;
import com.petkov.spr_final_1.model.view.DocumentViewModel;
import com.petkov.spr_final_1.repository.DocumentRepository;
import com.petkov.spr_final_1.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public DocumentServiceModel findDocumentByName(String documentRef) {
        DocumentEntity documentEntity = documentRepository
                .findByDocumentName(documentRef)
                .orElseThrow(() -> new IllegalArgumentException("Document could not be found in DB"));

        return modelMapper.map(documentEntity, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentViewModel> getAllDocumentsSortedAlphabeticallyDesc() {

        return documentRepository
                .findAll(Sort.by(Sort.Direction.DESC, "documentName"))
                .stream()
                .map(documentEntity -> modelMapper.map(documentEntity, DocumentViewModel.class))
                .collect(Collectors.toList());

    }




}
