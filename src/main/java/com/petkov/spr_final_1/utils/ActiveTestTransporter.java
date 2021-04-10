package com.petkov.spr_final_1.utils;

import com.petkov.spr_final_1.model.entity.test.ActiveTestEntity;

public class ActiveTestTransporter {

    // todo ActiveTestTransporter - rename

    private ActiveTestEntity activeTestEntity;

    public ActiveTestTransporter() {
    }

    public ActiveTestEntity getActiveTestViewModel() {
        return activeTestEntity;
    }

    public void setActiveTestViewModel(ActiveTestEntity activeTestEntity) {
        this.activeTestEntity = activeTestEntity;
    }
}
