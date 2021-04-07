package com.petkov.spr_final_1.model.rest;

import com.petkov.spr_final_1.model.view.ATAChapterViewModel;

import java.util.ArrayList;
import java.util.List;

public class ATAChapterEditErrorsViewModel extends ATAChapterViewModel {

    private List<String> bindingErrors = new ArrayList<>();

    public ATAChapterEditErrorsViewModel() {
    }

    public List<String> getBindingErrors() {
        return bindingErrors;
    }

    public void setBindingErrors(List<String> bindingErrors) {
        this.bindingErrors = bindingErrors;
    }
}
