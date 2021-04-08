package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.document.DocumentEntity;
import com.petkov.spr_final_1.model.entity.document.DocumentSubchapterEntity;
import com.petkov.spr_final_1.model.service.document.DocumentServiceModel;
import com.petkov.spr_final_1.model.service.document.DocumentSubchapterServiceModel;
import com.petkov.spr_final_1.model.view.DocumentSubchapterViewModel;
import com.petkov.spr_final_1.model.view.DocumentViewModel;
import com.petkov.spr_final_1.repository.DocumentSubchapterRepository;
import com.petkov.spr_final_1.service.DocumentService;
import com.petkov.spr_final_1.service.DocumentSubChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentSubChapterServiceImpl implements DocumentSubChapterService {

    private final DocumentSubchapterRepository documentSubchapterRepository;
    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    public DocumentSubChapterServiceImpl(DocumentSubchapterRepository documentSubchapterRepository,
                                         DocumentService documentService,
                                         ModelMapper modelMapper) {
        this.documentSubchapterRepository = documentSubchapterRepository;
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean subChapterExistsInDocument(String documentRef, String docSubchapterName) {

        DocumentEntity documentEntity =
                modelMapper.map(documentService.findDocumentByName(documentRef), DocumentEntity.class);

        if (documentEntity.getDocSubchapters() != null) {
            return documentEntity.getDocSubchapters().contains(docSubchapterName);
        }

        return false;
    }

    @Override
    public void seedDocumentSubchapterToDb(DocumentSubchapterServiceModel serviceModel) {

        DocumentSubchapterEntity documentSubchapterEntity =
                modelMapper.map(serviceModel, DocumentSubchapterEntity.class);

        DocumentEntity documentEntity
                = modelMapper.map(documentService.findDocumentByName(serviceModel.getDocumentRef()),
                DocumentEntity.class);

        documentSubchapterEntity.setDocument(documentEntity);

        documentSubchapterRepository.saveAndFlush(documentSubchapterEntity);

    }

    @Override
    public List<DocumentSubchapterViewModel> getAllSortedByNameDesc() {
        return documentSubchapterRepository
                .findAll(Sort.by(Sort.Direction.DESC, "docSubchapterName"))
                .stream()
                .map(documentSubchapterEntity ->
                        modelMapper.map(documentSubchapterEntity, DocumentSubchapterViewModel.class))
                .collect(Collectors.toList());
    }
}
