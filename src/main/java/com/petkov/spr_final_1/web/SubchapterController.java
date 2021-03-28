package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.SubChapterAddBindingModel;
import com.petkov.spr_final_1.model.service.SubChapterServiceModel;
import com.petkov.spr_final_1.service.ChapterService;
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
public class SubchapterController {

    private final ATASubchapterService ataSubchapterService;
    private final ChapterService ataChapterService;
    private final ModelMapper modelMapper;

    public SubchapterController(ATASubchapterService ataSubchapterService, ChapterService ataChapterService, ModelMapper modelMapper) {
        this.ataSubchapterService = ataSubchapterService;
        this.ataChapterService = ataChapterService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("subChapterAddBindingModel")
    public SubChapterAddBindingModel subChapterAddBindingModel() {
        return new SubChapterAddBindingModel();
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
    public String addSubChapterConfirm(@Valid SubChapterAddBindingModel subChapterAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("subChapterAddBindingModel", subChapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.subChapterAddBindingModel", bindingResult);

            return "redirect:/subchapters";
        }

        if (ataSubchapterService
                .subChapterCodeExists(
                        subChapterAddBindingModel.getAtaSubCode(),
                        subChapterAddBindingModel.getAtaChapterRefInput())) {

            redirectAttributes.addFlashAttribute("subChapterAddBindingModel", subChapterAddBindingModel);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.subChapterAddBindingModel", bindingResult);

            redirectAttributes.addFlashAttribute("ataSubChapterExistsError", true);

            return "redirect:/subchapters";
        }

        SubChapterServiceModel subChapterServiceModel = modelMapper.map(
                subChapterAddBindingModel,
                SubChapterServiceModel.class);

        ataSubchapterService.seedATASubchapterToDb(subChapterServiceModel);

        redirectAttributes.addFlashAttribute("seedOk", true);

        return "redirect:/subchapters";
    }
}
