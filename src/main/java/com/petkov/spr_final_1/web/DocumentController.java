package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.ATAChapterAddBindingModel;
import com.petkov.spr_final_1.model.binding.ATASubChapterAddBindingModel;
import com.petkov.spr_final_1.model.service.ATAChapterServiceModel;
import com.petkov.spr_final_1.model.service.ATASubChapterServiceModel;
import com.petkov.spr_final_1.service.ATASubchapterService;
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
@RequestMapping("/documents")
public class DocumentController {

    private final ATAChapterService ATAChapterService;
    private final ATASubchapterService ataSubchapterService;
    private final ModelMapper modelMapper;

    public DocumentController(ATAChapterService ATAChapterService, ATASubchapterService ataSubchapterService, ModelMapper modelMapper) {
        this.ATAChapterService = ATAChapterService;
        this.ataSubchapterService = ataSubchapterService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add-references")
    private String addReference(Model model) {

        if (!model.containsAttribute("redirectFrom")) {
            model.addAttribute("redirectFrom", "add-document-post");
        }

        model.addAttribute("ataDBList", this.ATAChapterService.listAllChaptersAtaAndNameOrderByAtaDesc());

        return "add-references";
    }


    @ModelAttribute("ataChapterAddBindingModel")
    public ATAChapterAddBindingModel ataChapterAddBindingModel() {
        return new ATAChapterAddBindingModel();
    }

    @GetMapping("/add-chapter")
    private String addChapter(Model model) {

        if (!model.containsAttribute("redirectFrom")) {
            model.addAttribute("redirectFrom", "add-chapter-post");
        }

        if (!model.containsAttribute("seedOk")) {
            model.addAttribute("seedOk", "false");
        }

        return "add-references";
    }

    @PostMapping("/add-chapter")
    public String addChapterConfirm(@Valid ATAChapterAddBindingModel ataChapterAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("redirectFrom", "add-chapter-post");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ataChapterAddBindingModel", ataChapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataChapterAddBindingModel", bindingResult);

            return "redirect:add-references";
        }

        //todo - addChapter - check if chapter exists and redirect to add-chapter page
        ATAChapterServiceModel ataChapterServiceModel = modelMapper.map(
                ataChapterAddBindingModel,
                ATAChapterServiceModel.class);

        ATAChapterService.addChapterToDB(ataChapterServiceModel);

        redirectAttributes.addFlashAttribute("seedOk", true);
        return "redirect:add-references";
    }


    @ModelAttribute("ataSubChapterAddBindingModel")
    public ATASubChapterAddBindingModel ataSubChapterAddBindingModel() {
        return new ATASubChapterAddBindingModel();
    }

    @GetMapping("/add-subChapter")
    private String addSubChapter(Model model) {

        if (!model.containsAttribute("redirectFrom")) {
            model.addAttribute("redirectFrom", "add-subChapter-post");
        }

        if (!model.containsAttribute("ataSubChapterExistsError")) {
            model.addAttribute("ataSubChapterExistsError", false);
        }

        if (!model.containsAttribute("seedOk")) {
            model.addAttribute("seedOk", false);
        }

        return "add-references";
    }

    @PostMapping("/add-subChapter")
    public String addSubChapterConfirm(@Valid ATASubChapterAddBindingModel ataSubChapterAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("redirectFrom", "add-subChapter-post");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ataSubChapterAddBindingModel", ataSubChapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataSubChapterAddBindingModel", bindingResult);

            return "redirect:add-references";
        }

        //todo - addSubChapterConfirm - check if subChapter exists and redirect to add-subChapter page

        if (ataSubchapterService.subChapterAtaCodeExists(ataSubChapterAddBindingModel.getAtaSubCode())) {

            redirectAttributes.addFlashAttribute("ataSubChapterAddBindingModel", ataSubChapterAddBindingModel);
            redirectAttributes.addFlashAttribute("ataSubChapterExistsError", true);

            return "redirect:/users/register";
        }
        ATASubChapterServiceModel ataSubChapterServiceModel = modelMapper.map(
                ataSubChapterAddBindingModel,
                ATASubChapterServiceModel.class);

        ataSubchapterService.seedATASubchapterToDb(ataSubChapterServiceModel);

        redirectAttributes.addFlashAttribute("seedOk", true);

        return "redirect:add-references";
    }


}
