package com.petkov.spr_final_1.init;

import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {



    //services
    private final UserService userService;
    private final ChapterService chapterService;

    public DBInit(UserService userService, ChapterService chapterService) {
        this.userService = userService;
        this.chapterService = chapterService;
    }


    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        this.chapterService.initSeedChapters();

    }

}
