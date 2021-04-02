package com.petkov.spr_final_1.model.binding;

import com.petkov.spr_final_1.model.view.ActiveTestViewModel;
import org.springframework.stereotype.Component;

public class ActiveTestTransporter {

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
