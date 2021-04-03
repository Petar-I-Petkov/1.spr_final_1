package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.utils.ActiveTestTransporter;
import com.petkov.spr_final_1.model.binding.test.SubmitTestBindingModel;
import com.petkov.spr_final_1.model.view.ActiveTestViewModel;
import com.petkov.spr_final_1.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    //todo ActiveTestTransporter - move in the service?
    private final ActiveTestTransporter activeTestTransporter;

    public TestController(TestService testService, ActiveTestTransporter activeTestTransporter) {
        this.testService = testService;
        this.activeTestTransporter = activeTestTransporter;
    }


    @GetMapping("/upcoming")
    private String viewAllUpcomingTests(Model model) {

        //todo - make getAllUpcomingTestsView return Upcoming tests, not all tests
        model.addAttribute("upcomingTests", testService.getAllUpcomingTestsView());

        return "tests-upcomming";
    }


    @GetMapping("/active/{id}")
    public String activeTestShow(@PathVariable String id, Model model) {

        ActiveTestViewModel activeTestViewModel = testService.getActiveTestById(id);

        model.addAttribute("activeTestViewModel", activeTestViewModel);

        activeTestTransporter.setActiveTestViewModel(activeTestViewModel);

        return "tests-active";
    }

    @PostMapping("/active/{id}")
    public String activeTestSubmit(@ModelAttribute("submitTestBindingModel") SubmitTestBindingModel submitTestBindingModel,
                                   Model model) {


        ActiveTestViewModel activeTestViewModel = activeTestTransporter.getActiveTestViewModel();

        //todo activeTestSubmit() debugPoint
        System.out.println();

        return "redirect:tests-result";
    }

    @GetMapping("/add")
    public String addTest() {


        return "tests-add";
    }

}
