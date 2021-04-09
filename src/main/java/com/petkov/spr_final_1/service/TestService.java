package com.petkov.spr_final_1.service;

import com.petkov.spr_final_1.model.service.test.TestServiceModel;
import com.petkov.spr_final_1.model.view.ActiveTestViewModel;
import com.petkov.spr_final_1.model.view.TestThumbnailViewModel;

import java.util.List;

public interface TestService {

    ActiveTestViewModel getActiveTestById(String id);

    List<TestThumbnailViewModel> getAllUpcomingTestsView();

    TestServiceModel seedTestToDb(TestServiceModel testServiceModel);
}
