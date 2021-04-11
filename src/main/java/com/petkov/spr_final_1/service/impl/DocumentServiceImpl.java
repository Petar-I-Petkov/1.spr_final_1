package com.petkov.spr_final_1.service.impl;

import com.google.gson.Gson;
import com.petkov.spr_final_1.model.entity.document.DocumentEntity;
import com.petkov.spr_final_1.model.entity.document.DocumentSubchapterEntity;
import com.petkov.spr_final_1.model.service.document.DocumentServiceModel;
import com.petkov.spr_final_1.model.view.DocumentViewModel;
import com.petkov.spr_final_1.repository.DocumentRepository;
import com.petkov.spr_final_1.service.DocumentService;
import com.petkov.spr_final_1.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.petkov.spr_final_1.constants.ExceptionMessages.*;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final String DEFAULT_REFERENCE_NAME = "other";

    private final ModelMapper modelMapper;
    private final DocumentRepository documentRepository;
    private final Resource documentInitFile;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    //todo - service - extract constants


    public DocumentServiceImpl(ModelMapper modelMapper,
                               DocumentRepository documentRepository,
                               @Value("classpath:init/documents-init.json") Resource documentInitFile,
                               Gson gson,
                               ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.documentRepository = documentRepository;
        this.documentInitFile = documentInitFile;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public void initSeedDocumentsFromJson() {
        if (documentRepository.count() == 0) {
            try {
                DocumentServiceModel[] documentServiceModels =
                        gson.fromJson(Files.readString(Path.of(documentInitFile.getURI())), DocumentServiceModel[].class);

                Arrays
                        .stream(documentServiceModels)
                        .forEach(this::seedDocumentsIfValidOrPrintError);

                // TODO: 4/11/2021 -  initSeedDocumentsFromJson - add successfull seed message

            } catch (IOException e) {
                throw new IllegalStateException("IO error from file 'init/documents-init.json'!");
            }
        }
    }

    private void seedDocumentsIfValidOrPrintError(DocumentServiceModel documentServiceModel) {

        if (this.validationUtil.isValid(documentServiceModel)) {

            DocumentEntity documentEntity = modelMapper.map(documentServiceModel, DocumentEntity.class);
            documentRepository.saveAndFlush(documentEntity);

        } else {
            //todo seedChaptersIfValidOrPrintError - Log errors io printing
            System.out.println(String.format("Document init seed errors from file 'init/documents-init.json':%n "));

            validationUtil.getViolations(documentServiceModel)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            System.out.printf("For document '%s'%n", documentServiceModel.getDocumentName());
        }
    }

    @Override
    public boolean documentExists(String documentName) {
        return documentRepository
                .findByDocumentName(documentName.toLowerCase().trim())
                .isPresent();
    }

    @Override
    @Transactional
    public DocumentServiceModel seedDocumentToDb(DocumentServiceModel documentServiceModel) {

        DocumentEntity documentEntity = this.modelMapper.map(documentServiceModel, DocumentEntity.class);

        setDefaultSubchapterIfMissing(documentEntity);

        documentEntity.setDocumentName(documentEntity.getDocumentName().toLowerCase().trim());

        return modelMapper.map(documentRepository.saveAndFlush(documentEntity), DocumentServiceModel.class);

    }

    private void setDefaultSubchapterIfMissing(DocumentEntity documentEntity) {

        if (documentEntity.getId() == null || documentEntity.getId().isBlank()) {

            DocumentSubchapterEntity defaultSubchapter = new DocumentSubchapterEntity();
            defaultSubchapter.setDocSubchapterName(DEFAULT_REFERENCE_NAME);
            documentEntity.setDocSubchapters(List.of(defaultSubchapter));
            defaultSubchapter.setDocument(documentEntity);
        }
    }

    @Override
    public DocumentServiceModel findDocumentByName(String documentName) {

        DocumentEntity documentEntity = documentRepository
                .findByDocumentName(documentName)
                .orElseThrow(() -> new IllegalArgumentException(DOCUMENT_NOT_FOUND));

        return modelMapper.map(documentEntity, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentViewModel> findAllDocumentsSortedByNameDesc() {

        return documentRepository
                .findAll(Sort.by(Sort.Direction.DESC, "documentName"))
                .stream()
                .map(documentEntity -> modelMapper.map(documentEntity, DocumentViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public DocumentServiceModel findDocumentById(String id) {
        DocumentEntity documentEntity = documentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document could not be found in DB"));

        return modelMapper.map(documentEntity, DocumentServiceModel.class);
    }

    @Override
    @Async
    public CompletableFuture<List<DocumentViewModel>> findAllDocumentsSortedByNameDescAsync() {
        return CompletableFuture
                .supplyAsync(this::findAllDocumentsSortedByNameDesc)
                .orTimeout(30, TimeUnit.SECONDS);

    }


}
