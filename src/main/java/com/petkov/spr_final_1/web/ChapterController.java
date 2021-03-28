package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.ChapterAddBindingModel;
import com.petkov.spr_final_1.model.service.ChapterServiceModel;
import com.petkov.spr_final_1.service.ChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterService chapterService;
    private final ModelMapper modelMapper;

    public ChapterController(ChapterService chapterService, ModelMapper modelMapper) {
        this.chapterService = chapterService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("chapterAddBindingModel")
    public ChapterAddBindingModel chapterAddBindingModel() {
        return new ChapterAddBindingModel();
    }

    @GetMapping("")
    private String addChapter(Model model) {

        if (!model.containsAttribute("seedOk")) {
            model.addAttribute("seedOk", "false");
        }

        if (!model.containsAttribute("chapterExistsError")) {
            model.addAttribute("chapterExistsError", false);
        }

        return "chapters";
    }

    @PostMapping("")
    public String addChapterConfirm(@Valid ChapterAddBindingModel chapterAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("chapterAddBindingModel", chapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.chapterAddBindingModel", bindingResult);

            return "redirect:/chapters";
        }

        if (chapterService.chapterAtaCodeExists(chapterAddBindingModel.getAtaCode())) {

            redirectAttributes.addFlashAttribute("chapterAddBindingModel", chapterAddBindingModel);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.chapterAddBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("chapterExistsError", true);

            return "redirect:/chapters";
        }

        ChapterServiceModel chapterServiceModel = modelMapper.map(
                chapterAddBindingModel,
                ChapterServiceModel.class);

        chapterService.addChapterToDB(chapterServiceModel);

        redirectAttributes.addFlashAttribute("seedOk", true);

        return "redirect:/chapters";
    }


}
