package com.petkov.spr_final_1.DEVTools;

import com.petkov.spr_final_1.constants.Paths;
import com.petkov.spr_final_1.repository.TestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.petkov.spr_final_1.constants.Paths.*;

@Component
public class AppController implements CommandLineRunner {

    private final TestRepository testRepository;

    public AppController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        long count = this.testRepository.count();
        System.out.printf("tests: %d%n", count);

        this.testRepository.findAll(Sort.by("name")).forEach(test -> {

            System.out.printf("Test: %s%n", test.getName());

            test.getQuestions()
                    .forEach(question -> {
                        System.out.printf("   Name: %s%n", question.getName());
                        System.out.printf("      Question: %s%n", question.getQuestion());
                        System.out.printf("          correct Answer: %s%n", question.getCorrectAnswer());
                        System.out.printf("          alt Answer1:     %s%n", question.getAltAnswer1());
                        System.out.printf("          alt Answer2:     %s%n", question.getAltAnswer2());
                        System.out.printf("          alt Answer3:     %s%n", question.getAltAnswer3());
                        System.out.printf("          alt Answer4:     %s%n", question.getAltAnswer4());
                    });

            pathOf(FCOM, ATA_70_ENGINES);
        });


    }

    private void pathOf(Paths... varArgs){

        List<String> stringList = Arrays.stream(varArgs)
                .map(Enum::name)
                .collect(Collectors.toList());

        System.out.println(String.join("/", stringList ));
    }

}
