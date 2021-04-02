package com.petkov.spr_final_1.web;


import com.petkov.spr_final_1.model.view.ATAChapterViewModel;
import com.petkov.spr_final_1.service.ATAChapterService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/ata-chapters")
@RestController
public class ATAChapterRestController {

    private final ATAChapterService chapterService;
    private final ModelMapper modelMapper;

    public ATAChapterRestController(ATAChapterService chapterService, ModelMapper modelMapper) {
        this.chapterService = chapterService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<ATAChapterViewModel>> getAllChaptersSortedByATA() {

        return ResponseEntity
                .ok()
                .body(chapterService
                        .getAllChaptersSortedByAtaDesc()
                        .stream()
                        .map(chapterServiceModel -> 
                                modelMapper.map(
                                        chapterServiceModel,
                                        ATAChapterViewModel.class))
                        .collect(Collectors.toList()));

    }
}
