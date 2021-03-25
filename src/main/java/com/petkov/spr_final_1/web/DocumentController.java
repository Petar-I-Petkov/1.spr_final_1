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

    @ModelAttribute("chapterAddBindingModel")
    public ChapterAddBindingModel chapterAddBindingModel() {
        return new ChapterAddBindingModel();
    }


    @GetMapping("/add-chapter")
    private String addChapter(Model model, HttpSession httpSession){

        return "document-add";
    }

    @PostMapping("/add-chapter")
    public String addChapterConfirm(@Valid ChapterAddBindingModel chapterAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("chapterAddBindingModel", chapterAddBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.chapterAddBindingModel", bindingResult);

            return "redirect:add-chapter";
        }


        //todo - addChapter - check if chapter exists and redirect to add-chapter page
        ChapterServiceModel chapterServiceModel = modelMapper.map(
                chapterAddBindingModel,
                ChapterServiceModel.class);

        chapterService.addChapterToDB(chapterServiceModel);

        return "redirect:/home";
    }


}
