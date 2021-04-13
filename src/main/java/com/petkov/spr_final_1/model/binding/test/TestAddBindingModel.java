package com.petkov.spr_final_1.model.binding.test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.petkov.spr_final_1.model.entity.UserEntity;
import com.petkov.spr_final_1.model.entity.enumeration.TestStatusEnum;
import com.petkov.spr_final_1.model.entity.enumeration.TestTagEnum;
import com.petkov.spr_final_1.model.entity.test.QuestionEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class TestAddBindingModel {

    private String name;
    private LocalDate dueDate;
    private List<String> questionIds;
    private List<TestTagEnum> testTagEnums;

    @NotBlank(message = "Test name cannot be empty.")
    @Size(min = 3, message = "Test name length min 3 characters.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Test due date is required.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Test due date cannot be in the past.")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @NotEmpty(message = "There must be at least one question in the test.")
    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

    @NotEmpty(message = "Test must have at lest one tag.")
    public List<TestTagEnum> getTestTagEnums() {
        return testTagEnums;
    }

    public void setTestTagEnums(List<TestTagEnum> testTagEnums) {
        this.testTagEnums = testTagEnums;
    }
}
