package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.documentEntities.ArticleEntity;
import com.petkov.spr_final_1.model.service.ArticleServiceModel;
import com.petkov.spr_final_1.repository.ArticleRepository;
import com.petkov.spr_final_1.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean articleExistsByTitle(String title) {
        return this.articleRepository.findByTitle(title).isPresent();
    }

    @Override
    public void seedArticleToDb(ArticleServiceModel articleServiceModel) {
        ArticleEntity articleEntity = modelMapper.map(articleServiceModel, ArticleEntity.class);

        //todo seedArticleToDb debug point
        System.out.println();
    }
}
