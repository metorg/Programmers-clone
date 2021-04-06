package com.sparta.programmersclone.controller;

import com.sparta.programmersclone.ProgrammersCloneApplication;
import com.sparta.programmersclone.crawling.SeleniumCrawling;
import com.sparta.programmersclone.entity.Problem;
import com.sparta.programmersclone.entity.ProgrammingLanguage;
import com.sparta.programmersclone.repository.ProblemRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.SerializedLambda;

@RestController
@RequiredArgsConstructor
//@NoArgsConstructor
public class ProblemController {
    private final ProblemRepository problemRepository;

    @GetMapping("/test")
    public void createProblem() throws InterruptedException {
//        Thread.sleep(3000);
        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        String[][] AllInfo = seleniumCrawling.activateBot();
//        System.out.println("방가");
//        Problem problem = new Problem("임의의 제목", "난이도", "출처", "언어");

        Problem problem;
        for (int i = 1; i < AllInfo.length; i++) {
            problem = new Problem(AllInfo[i][1], AllInfo[i][2], AllInfo[i][3], AllInfo[i][4]);
            problemRepository.save(problem);
        }

//        return problemRepository.save(problem);
    }
}
