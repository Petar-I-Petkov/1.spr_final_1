package com.petkov.spr_final_1.web;

import com.petkov.spr_final_1.model.binding.test.TestAddBindingModel;
import com.petkov.spr_final_1.model.service.test.TestAddServiceModel;
import com.petkov.spr_final_1.service.TestService;
import com.petkov.spr_final_1.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/tests/api")
@RestController
public class TestRestController {

    private final TestService testService;
    private final ModelMapper modelMapper;
    private final Logger LOGGER = LoggerFactory.getLogger(ATAChapterRestController.class);
    private final ValidationUtil validationUtil;

    public TestRestController(TestService testService, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.testService = testService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    //todo - add Aspect and/or EventListener to log at different place
//    @GetMapping(value = "", produces = "application/json")
//    public DeferredResult<ResponseEntity<List<ATAChapterViewModel>>> getAllChaptersSortedByATA() {
//
//        LOGGER.info("Received async-deferred request at </ata-chapters>");
//
//        DeferredResult<ResponseEntity<List<ATAChapterViewModel>>> deferredResult = new DeferredResult<>();
//
//
//        testService
//                .getAllChaptersSortedByAtaDesc()
//                .thenApply(ataChapterViewModels ->
//                        deferredResult.setResult(ResponseEntity
//                                .ok()
//                                .body(ataChapterViewModels))
//                )
//                .exceptionally(ex ->
//                        //todo - getAllChaptersSortedByATA handle ex with Aspect
//                        deferredResult.setResult(ResponseEntity
//                                .notFound()
//                                .build())
//                );
//
//        return deferredResult;
//    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TestAddServiceModel> addTest(@RequestBody @Valid TestAddBindingModel testAddBindingModel) {

            TestAddServiceModel testAddServiceModel =
                    this.modelMapper.map(testAddBindingModel, TestAddServiceModel.class);

            return ResponseEntity
                    .ok()
                    .body(testAddServiceModel);

    }
}
