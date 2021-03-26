package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.ATAChapterAddBindingModel;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    private final ChapterService chapterService;
    private final ModelMapper modelMapper;

    public DocumentController(ChapterService chapterService, ModelMapper modelMapper) {
        this.chapterService = chapterService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("ataChapterAddBindingModel")
    public ATAChapterAddBindingModel ataChapterAddBindingModel() {
        return new ATAChapterAddBindingModel();
    }


    @GetMapping("/add-references")
    private String addReference(Model model){

        if(!model.containsAttribute("redirectFrom")){
            model.addAttribute("redirectFrom", "add-document-post");
        }

        return "add-references";
    }

    @GetMapping("/add-chapter")
    private String addChapter(Model model){

        if(!model.containsAttribute("redirectFrom")){
            model.addAttribute("redirectFrom", "add-chapter-post");
        }

        if(!model.containsAttribute("seedOk")){
            model.addAttribute("seedOk", "false");
        }

        return "add-references";
    }

    @PostMapping("/add-chapter")
    public String addChapterConfirm(@Valid ATAChapterAddBindingModel ataChapterAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("redirectFrom", "add-chapter-post");

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("ataChapterAddBindingModel", ataChapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ataChapterAddBindingModel", bindingResult);

            return "redirect:add-references";
        }

        //todo - addChapter - check if chapter exists and redirect to add-chapter page
        ChapterServiceModel chapterServiceModel = modelMapper.map(
                ataChapterAddBindingModel,
                ChapterServiceModel.class);

        chapterService.addChapterToDB(chapterServiceModel);

        //todo addChapterConfirm - display success message and stay on tab using Model do pass data to addReference GetMapping
        redirectAttributes.addFlashAttribute("seedOk", true);
        return "redirect:add-references";
    }

}
