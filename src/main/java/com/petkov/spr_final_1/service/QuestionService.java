package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import com.petkov.spr_final_1.model.service.test.QuestionServiceModel;
import com.petkov.spr_final_1.model.view.ATAChapterViewModel;
import com.petkov.spr_final_1.model.view.QuestionViewModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public interface QuestionService {

    // todo QuestionService - remove the init DB method
    void saveQuestion(QuestionEntity questionEntity);

    boolean questionExistsByName(String name);

    void seedQuestionToDb(QuestionServiceModel questionServiceModel);

    CompletableFuture<List<QuestionViewModel>> getAllQuestionsSortedByATA();

    QuestionServiceModel findById(Long id);
}
