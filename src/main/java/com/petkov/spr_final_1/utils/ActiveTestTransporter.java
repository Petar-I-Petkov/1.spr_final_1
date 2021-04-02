package com.petkov.spr_final_1.utils;

import com.petkov.spr_final_1.model.view.ActiveTestViewModel;
import org.springframework.stereotype.Component;

public class ActiveTestTransporter {

    // todo ActiveTestTransporter - rename and make generic <T>

    private ActiveTestViewModel activeTestViewModel;

    public ActiveTestTransporter() {
    }

    public ActiveTestViewModel getActiveTestViewModel() {
        return activeTestViewModel;
    }

    public void setActiveTestViewModel(ActiveTestViewModel activeTestViewModel) {
        this.activeTestViewModel = activeTestViewModel;
    }
}
