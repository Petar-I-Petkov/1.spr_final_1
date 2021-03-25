package com.petkov.spr_final_1.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {


    public HomeController() {
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/users/login";
    }

    @GetMapping("/home")
    public String home(Model model) {

        return "home";
    }
}
