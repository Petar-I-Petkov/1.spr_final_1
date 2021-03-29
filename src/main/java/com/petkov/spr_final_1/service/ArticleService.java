package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.ArticleServiceModel;

public interface ArticleService {
    boolean articleExistsByTitle(String title);

    void seedArticleToDb(ArticleServiceModel articleServiceModel);
}
