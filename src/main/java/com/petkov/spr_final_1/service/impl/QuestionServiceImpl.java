package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.document.ArticleEntity;
import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import com.petkov.spr_final_1.model.service.test.QuestionServiceModel;
import com.petkov.spr_final_1.model.view.ArticleViewModel;
import com.petkov.spr_final_1.model.view.QuestionViewModel;
import com.petkov.spr_final_1.repository.QuestionRepository;
import com.petkov.spr_final_1.service.ArticleService;
import com.petkov.spr_final_1.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final ArticleService articleService;

    public QuestionServiceImpl(QuestionRepository questionRepository, ModelMapper modelMapper, ArticleService articleService) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.articleService = articleService;
    }

    @Override
    public void saveQuestion(QuestionEntity questionEntity) {
        this.questionRepository.save(questionEntity);
    }

    @Override
    public boolean questionExistsByName(String name) {
        return this.questionRepository.findByName(name).isPresent();
    }

    @Override
    public boolean questionExistsByQuestion(String question) {
        return false;
    }

    @Override
    public void seedQuestionToDb(QuestionServiceModel questionServiceModel) {

        QuestionEntity questionEntity =
                modelMapper.map(questionServiceModel, QuestionEntity.class);

        if (!questionServiceModel.getArticle().isBlank()) {

                ArticleEntity articleEntity = modelMapper
                        .map(articleService.getArticleByTitle(questionServiceModel.getArticle()), ArticleEntity.class);

                if(articleEntity != null){
                questionEntity.setArticle(articleEntity);
                }

        }


        //todo seedQuestionToDb debug point
        System.out.println();
        questionRepository.saveAndFlush(questionEntity);
    }

    @Override
    @Async
    public CompletableFuture<List<QuestionViewModel>> getAllQuestionsSortedByATA() {

        return CompletableFuture
                .supplyAsync(() -> questionRepository.findAll()
                        .stream()
                        .map(questionEntity -> modelMapper.map(questionEntity, QuestionViewModel.class))
                        .collect(Collectors.toList()))
                .orTimeout(30, TimeUnit.SECONDS);
    }


}
