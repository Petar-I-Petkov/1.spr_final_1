package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import com.petkov.spr_final_1.model.service.test.QuestionServiceModel;

public interface QuestionService {

    // todo QuestionService - remove the init DB method
    void saveQuestion(QuestionEntity questionEntity);

    boolean questionExistsByName(String name);

    boolean questionExistsByQuestion(String question);

    void seedQuestionToDb(QuestionServiceModel questionServiceModel);
}
