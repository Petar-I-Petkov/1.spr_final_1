package com.petkov.spr_final_1.web;


import com.petkov.spr_final_1.model.binding.document.ATAChapterAddBindingModel;
import com.petkov.spr_final_1.model.view.ATAChapterViewModel;
import com.petkov.spr_final_1.service.ATAChapterService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/ata-chapters/api")
@RestController
public class ATAChapterRestController {

    private final ATAChapterService chapterService;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(ATAChapterRestController.class);


    public ATAChapterRestController(ATAChapterService chapterService, ModelMapper modelMapper) {
        this.chapterService = chapterService;
        this.modelMapper = modelMapper;
    }

    //todo - add Aspect and/or EventListener to log at different place
    @GetMapping("")
    public DeferredResult<ResponseEntity<List<ATAChapterViewModel>>> getAllChaptersSortedByATA() {
        LOGGER.info("Received async-deferred request at </ata-chapters>");

        DeferredResult<ResponseEntity<List<ATAChapterViewModel>>> deferredResult = new DeferredResult<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        chapterService
                .getAllChaptersSortedByAtaDesc()
                .thenApply(ataChapterViewModels ->
                        deferredResult.setResult(ResponseEntity
                                .ok()
                                .headers(httpHeaders)
                                .body(ataChapterViewModels))
                )
                .exceptionally(ex ->
                        //todo - getAllChaptersSortedByATA handle ex with Aspect
                        deferredResult.setResult(ResponseEntity
                                .notFound()
                                .build())
                );

        return deferredResult;
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ATAChapterViewModel> editById(@PathVariable String id,
                                                        @RequestBody ATAChapterAddBindingModel ataChapterAddBindingModel){

        return ResponseEntity.noContent().build();
    }
}
