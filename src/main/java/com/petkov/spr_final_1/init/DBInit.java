package com.petkov.spr_final_1.init;

import com.petkov.spr_final_1.service.ChapterService;
import com.petkov.spr_final_1.service.SubChapterService;
import com.petkov.spr_final_1.service.TestService;
import com.petkov.spr_final_1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final ChapterService chapterService;
    private final SubChapterService subchapterService;
    private final TestService testService;

    public DBInit(UserService userService,
                  ChapterService chapterService,
                  SubChapterService subchapterService,
                  TestService testService) {
        this.userService = userService;
        this.chapterService = chapterService;
        this.subchapterService = subchapterService;
        this.testService = testService;
    }


    @Override
    public void run(String... args) throws Exception {

        userService.seedUsers();
        chapterService.initSeedChaptersFromJson();
        subchapterService.initSeedSubchaptersFromJson();





    }

}
