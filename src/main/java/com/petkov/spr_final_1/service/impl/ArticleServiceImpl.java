package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.document.ArticleEntity;
import com.petkov.spr_final_1.model.entity.document.ATAChapterEntity;
import com.petkov.spr_final_1.model.entity.document.ATASubChapterEntity;
import com.petkov.spr_final_1.model.service.document.ArticleServiceModel;
import com.petkov.spr_final_1.repository.ArticleRepository;
import com.petkov.spr_final_1.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    private final ATAChapterService chapterService;
    private final ATASubChapterService subChapterService;
    private final DocumentService documentService;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ModelMapper modelMapper,
                              CloudinaryService cloudinaryService,
                              ATAChapterService chapterService,
                              ATASubChapterService subChapterService,
                              DocumentService documentService) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.chapterService = chapterService;
        this.subChapterService = subChapterService;
        this.documentService = documentService;
    }

    @Override
    public boolean articleExistsByTitle(String title) {
        return this.articleRepository.findByTitle(title).isPresent();
    }

    @Override
    public void seedArticleToDb(ArticleServiceModel articleServiceModel) throws IOException {

        ArticleEntity articleEntity = modelMapper.map(articleServiceModel, ArticleEntity.class);

        if (!articleServiceModel.getImage().isEmpty()) {
            MultipartFile img = articleServiceModel.getImage();
            String imageUrl = cloudinaryService.uploadImage(img);
            articleEntity.setImageUrl(imageUrl);
        }

        if (!articleServiceModel.getAtaSubChapterRef().isEmpty() && !articleServiceModel.getChapterRef().isEmpty()) {

            int chapterRef = Integer.parseInt(articleServiceModel.getChapterRef().split(" ")[0]);

            // todo seedArticleToDb - add try catch
            ATAChapterEntity chapterEntity =
                    modelMapper.map(chapterService.findChapterByAtaCode(chapterRef), ATAChapterEntity.class);

            int subChapterRef = Integer.parseInt(articleServiceModel.getAtaSubChapterRef().split(" ")[0]);
            ATASubChapterEntity subChapterEntity =
                    modelMapper.map(subChapterService
                            .findByChapterAndSubchapterAta(chapterRef, subChapterRef), ATASubChapterEntity.class);

            articleEntity.setChapter(chapterEntity);
            articleEntity.setAtaSubChapter(subChapterEntity);

        } else if (!articleServiceModel.getChapterRef().isEmpty()) {
            int chapterRef = Integer.parseInt(articleServiceModel.getChapterRef().split(" ")[0]);
            ATAChapterEntity chapterEntity =
                    modelMapper.map(chapterService.findChapterByAtaCode(chapterRef), ATAChapterEntity.class);

            articleEntity.setChapter(chapterEntity);

        }

        //todo - refactor articleEntity.setDocument(documentEntity) to
        // articleEntity.setDocumentSubchapter(documentSubchapterEntity)
//        if (!articleServiceModel.getDocumentRef().isEmpty()) {
//            String documentRef = articleServiceModel.getDocumentRef();
//            DocumentEntity documentEntity =
//                    modelMapper.map(documentService.findDocumentByName(documentRef), DocumentEntity.class);
//
//            articleEntity.setDocument(documentEntity);
//        }


        //todo seedArticleToDb debug point
        System.out.println();

        articleRepository.saveAndFlush(articleEntity);
    }

    @Override
    public ArticleServiceModel getArticleByTitle(String title) throws IllegalArgumentException {

        ArticleEntity articleEntity = articleRepository
                .findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Article not found in DB"));

        return modelMapper.map(articleEntity, ArticleServiceModel.class);
    }
}
