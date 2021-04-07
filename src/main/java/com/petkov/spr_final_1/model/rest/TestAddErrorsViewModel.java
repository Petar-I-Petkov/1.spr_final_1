package com.petkov.spr_final_1.model.rest;

import com.petkov.spr_final_1.model.view.TestThumbnailViewModel;

import java.util.ArrayList;
import java.util.List;

public class TestAddErrorsViewModel extends TestThumbnailViewModel {

    private List<String> bindingErrors = new ArrayList<>();

    public TestAddErrorsViewModel() {
    }

    public List<String> getBindingErrors() {
        return bindingErrors;
    }

    public void setBindingErrors(List<String> bindingErrors) {
        this.bindingErrors = bindingErrors;
    }
}
