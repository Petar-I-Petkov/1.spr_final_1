package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import com.petkov.spr_final_1.repository.QuestionRepository;
import com.petkov.spr_final_1.service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void saveQuestion(QuestionEntity questionEntity) {
        this.questionRepository.save(questionEntity);
    }
}
