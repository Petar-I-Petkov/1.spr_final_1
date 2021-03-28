package com.petkov.spr_final_1.web;


import com.petkov.spr_final_1.model.view.ChapterViewModel;
import com.petkov.spr_final_1.service.ChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/chapters")
@RestController
public class ChapterRestController {

    private final ChapterService chapterService;
    private final ModelMapper modelMapper;

    public ChapterRestController(ChapterService chapterService, ModelMapper modelMapper) {
        this.chapterService = chapterService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<ChapterViewModel>> getAllChaptersSortedByATA() {

        return ResponseEntity
                .ok()
                .body(chapterService
                        .findAllChaptersSortedByATA()
                        .stream()
                        .map(chapterServiceModel -> 
                                modelMapper.map(
                                        chapterServiceModel,
                                        ChapterViewModel.class))
                        .collect(Collectors.toList()));

    }
}
