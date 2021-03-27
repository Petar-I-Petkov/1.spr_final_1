package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.ATAChapterAddBindingModel;
import com.petkov.spr_final_1.model.service.ATAChapterServiceModel;
import com.petkov.spr_final_1.service.ATAChapterService;
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
public class ATAChapterController {

    private final ATAChapterService ataChapterService;
    private final ModelMapper modelMapper;

    public ATAChapterController(ATAChapterService ataChapterService, ModelMapper modelMapper) {
        this.ataChapterService = ataChapterService;
        this.modelMapper = modelMapper;
    }


    @ModelAttribute("ataChapterAddBindingModel")
    public ATAChapterAddBindingModel ataChapterAddBindingModel() {
        return new ATAChapterAddBindingModel();
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
    public String addChapterConfirm(@Valid ATAChapterAddBindingModel ataChapterAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ataChapterAddBindingModel", ataChapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataChapterAddBindingModel", bindingResult);

            return "redirect:/chapters";
        }

        if (ataChapterService.chapterAtaCodeExists(ataChapterAddBindingModel.getAtaChapter())) {

            redirectAttributes.addFlashAttribute("ataChapterAddBindingModel", ataChapterAddBindingModel);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataChapterAddBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("chapterExistsError", true);

            return "redirect:/chapters";
        }

        ATAChapterServiceModel ataChapterServiceModel = modelMapper.map(
                ataChapterAddBindingModel,
                ATAChapterServiceModel.class);

        ataChapterService.addChapterToDB(ataChapterServiceModel);

        redirectAttributes.addFlashAttribute("seedOk", true);

        return "redirect:/chapters";
    }


}
