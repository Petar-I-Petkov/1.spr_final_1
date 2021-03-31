package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("/upcoming")
    private String viewAllUpcomingTests(Model model) {

        model.addAttribute("upcomingTests", testService.getAllUpcomingTestsView());

        return "tests";
    }









//    @GetMapping("/active/{id}")
//    public String details(@PathVariable Long id, Model model){
//
//        AlbumViewModel albumViewModel = albumService.findById(id);
//
//        model.addAttribute("album", albumViewModel);
//
//        return "active-test";
//    }

}
