package com.petkov.spr_final_1.service.impl;

import com.petkov.spr_final_1.model.entity.test.TestEntity;
import com.petkov.spr_final_1.model.service.test.TestServiceModel;
import com.petkov.spr_final_1.model.view.ActiveQuestionViewModel;
import com.petkov.spr_final_1.model.view.ActiveTestViewModel;
import com.petkov.spr_final_1.model.view.ArticleViewModel;
import com.petkov.spr_final_1.model.view.TestThumbnailViewModel;
import com.petkov.spr_final_1.repository.TestRepository;
import com.petkov.spr_final_1.service.ArticleService;
import com.petkov.spr_final_1.service.QuestionService;
import com.petkov.spr_final_1.service.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final ModelMapper modelMapper;
    private final ArticleService articleService;
    private final QuestionService questionService;

    public TestServiceImpl(TestRepository testRepository,
                           ModelMapper modelMapper,
                           ArticleService articleService,
                           QuestionService questionService) {
        this.testRepository = testRepository;
        this.modelMapper = modelMapper;
        this.articleService = articleService;
        this.questionService = questionService;
    }

    @Override
    @Transactional
    public ActiveTestViewModel getActiveTestById(String id) {

        TestEntity testEntity =
                testRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(String.format("Test with id '%s' not found", id)));

        ActiveTestViewModel activeTestViewModel
                = modelMapper.map(testEntity, ActiveTestViewModel.class);

        int[] index = new int[]{0};

        List<ActiveQuestionViewModel> activeQuestionViewModels =
                testEntity.getQuestions()
                        .stream()
                        .map(questionEntity -> {

                            ActiveQuestionViewModel activeQuestionViewModel =
                                    modelMapper.map(questionEntity, ActiveQuestionViewModel.class);

                            //set article
                            ArticleViewModel articleViewModel =
                                    modelMapper.map(questionEntity.getArticle(), ArticleViewModel.class);

                            activeQuestionViewModel.setArticleViewModel(articleViewModel);

                            //set answers
                            List<String> answers = List.of(
                                    questionEntity.getCorrectAnswer(),
                                    questionEntity.getAltAnswer1(),
                                    questionEntity.getAltAnswer2(),
                                    questionEntity.getAltAnswer3(),
                                    questionEntity.getAltAnswer4()
                            );

                            activeQuestionViewModel.setAnswers(answers);

                            //populate the correct/given answer matrices
                            activeTestViewModel.getCorrectAnswerMatrix().put(index[0], questionEntity.getCorrectAnswer());
                            activeTestViewModel.getGivenAnswerMatrix().put(index[0],"not answered");
                            index[0]++;

                            return activeQuestionViewModel;
                        })
                        .collect(Collectors.toList());




        activeTestViewModel.setQuestionEntities(activeQuestionViewModels);

        //todo ActiveTestViewModel debug point
        System.out.println();

        return activeTestViewModel;
    }

    @Override
    @Transactional
    public List<TestThumbnailViewModel> getAllUpcomingTestsView() {

        return testRepository.findAll()
                .stream()
                .map(testEntity -> {
                    TestThumbnailViewModel testThumbnailViewModel =
                            modelMapper.map(testEntity, TestThumbnailViewModel.class);

                    testThumbnailViewModel.setNumberOfQuestions(testEntity.getQuestions().size());


                    return testThumbnailViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TestServiceModel seedTestToDb(TestServiceModel testServiceModel) {

    }
}
