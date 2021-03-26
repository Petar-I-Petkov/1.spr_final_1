package com.petkov.spr_final_1.init;

import com.petkov.spr_final_1.service.ATAChapterService;
import com.petkov.spr_final_1.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {



    //services
    private final UserService userService;
    private final ATAChapterService ATAChapterService;

    public DBInit(UserService userService, ATAChapterService ATAChapterService) {
        this.userService = userService;
        this.ATAChapterService = ATAChapterService;
    }


    @Override
    public void run(String... args) throws Exception {
        userService.seedUsers();
        this.ATAChapterService.initSeedChapters();

        this.ATAChapterService.listAllChaptersAtaAndNameOrderByAtaDesc();

    }

}
