package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ArticleServiceModel;

import java.io.IOException;

public interface ArticleService {
    boolean articleExistsByTitle(String title);

    void seedArticleToDb(ArticleServiceModel articleServiceModel) throws IOException;
}
