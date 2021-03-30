package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ArticleEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.ChapterEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.DocumentEntity;
import com.petkov.spr_final_1.model.entity.documentEntities.SubChapterEntity;
import com.petkov.spr_final_1.model.service.ArticleServiceModel;
import com.petkov.spr_final_1.model.service.SubChapterServiceModel;
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

    private final ChapterService chapterService;
    private final SubChapterService subChapterService;
    private final DocumentService documentService;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ModelMapper modelMapper,
                              CloudinaryService cloudinaryService,
                              ChapterService chapterService,
                              SubChapterService subChapterService,
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
            ChapterEntity chapterEntity =
                    modelMapper.map(chapterService.findChapterByAtaCode(chapterRef), ChapterEntity.class);

            int subChapterRef = Integer.parseInt(articleServiceModel.getAtaSubChapterRef().split(" ")[0]);
            SubChapterEntity subChapterEntity =
                    modelMapper.map(subChapterService
                            .findByChapterAndSubchapterAta(chapterRef, subChapterRef), SubChapterEntity.class);

            articleEntity.setChapter(chapterEntity);
            articleEntity.setAtaSubChapter(subChapterEntity);

        } else if (!articleServiceModel.getChapterRef().isEmpty()) {
            int chapterRef = Integer.parseInt(articleServiceModel.getChapterRef().split(" ")[0]);
            ChapterEntity chapterEntity =
                    modelMapper.map(chapterService.findChapterByAtaCode(chapterRef), ChapterEntity.class);

            articleEntity.setChapter(chapterEntity);

        }

        if (!articleServiceModel.getDocumentRef().isEmpty()) {
            String documentRef = articleServiceModel.getDocumentRef();
            DocumentEntity documentEntity =
                    modelMapper.map(documentService.findDocumentByName(documentRef), DocumentEntity.class);

            articleEntity.setDocument(documentEntity);
        }



        //todo seedArticleToDb debug point
        System.out.println();
    }
}
