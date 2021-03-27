package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.ATASubChapterAddBindingModel;
import com.petkov.spr_final_1.model.service.ATASubChapterServiceModel;
import com.petkov.spr_final_1.service.ATAChapterService;
import com.petkov.spr_final_1.service.ATASubchapterService;
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
@RequestMapping("/subchapters")
public class ATASubchapterController {

    private final ATASubchapterService ataSubchapterService;
    private final ATAChapterService ataChapterService;
    private final ModelMapper modelMapper;

    public ATASubchapterController(ATASubchapterService ataSubchapterService, ATAChapterService ataChapterService, ModelMapper modelMapper) {
        this.ataSubchapterService = ataSubchapterService;
        this.ataChapterService = ataChapterService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("ataSubChapterAddBindingModel")
    public ATASubChapterAddBindingModel ataSubChapterAddBindingModel() {
        return new ATASubChapterAddBindingModel();
    }

    @GetMapping("")
    private String addSubChapter(Model model) {

        if (!model.containsAttribute("ataSubChapterExistsError")) {
            model.addAttribute("ataSubChapterExistsError", false);
        }

        if (!model.containsAttribute("seedOk")) {
            model.addAttribute("seedOk", false);
        }

        model.addAttribute("ataDBList", this.ataChapterService.listAllChaptersAtaAndNameOrderByAtaDesc());


        return "subchapters";
    }

    @PostMapping("")
    public String addSubChapterConfirm(@Valid ATASubChapterAddBindingModel ataSubChapterAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ataSubChapterAddBindingModel", ataSubChapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataSubChapterAddBindingModel", bindingResult);

            return "redirect:/subchapters";
        }

        if (ataSubchapterService
                .subChapterCodeExists(
                        ataSubChapterAddBindingModel.getAtaSubCode(),
                        ataSubChapterAddBindingModel.getAtaChapterRefInput())) {

            redirectAttributes.addFlashAttribute("ataSubChapterAddBindingModel", ataSubChapterAddBindingModel);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataSubChapterAddBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("ataSubChapterExistsError", true);

            return "redirect:/subchapters";
        }

        ATASubChapterServiceModel ataSubChapterServiceModel = modelMapper.map(
                ataSubChapterAddBindingModel,
                ATASubChapterServiceModel.class);

        ataSubchapterService.seedATASubchapterToDb(ataSubChapterServiceModel);

        redirectAttributes.addFlashAttribute("seedOk", true);

        return "redirect:/subchapters";
    }
}
