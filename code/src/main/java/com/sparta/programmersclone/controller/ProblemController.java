package com.sparta.programmersclone.controller;

import com.sparta.programmersclone.Service.ProblemService;
import com.sparta.programmersclone.Service.ProgrammingLanguageService;
import com.sparta.programmersclone.crawling.SeleniumCrawling;
import com.sparta.programmersclone.dto.ProblemRequestDto;
import com.sparta.programmersclone.dto.ProgrammingLanguageRequestDto;
import com.sparta.programmersclone.entity.Problem;
import com.sparta.programmersclone.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemRepository problemRepository;
    private final ProblemService service;
    private final ProgrammingLanguageService programmingLanguageService;


    @GetMapping("/save")
    public void createProblem() throws InterruptedException {
        System.out.println("----------------확인------------------");
        ProblemRequestDto problemRequestDto = new ProblemRequestDto();
        ProgrammingLanguageRequestDto programmingLanguageRequestDto = new ProgrammingLanguageRequestDto();
        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        String[][] AllInfo = seleniumCrawling.activateBot();
//      System.out.println("방가");
//      Problem problem = new Problem("임의의 제목", "난이도", "출처", "언어");

        Problem problem;
        for (int i = 1; i < AllInfo.length; i++) {
//            problem = new Problem(AllInfo[i][1], AllInfo[i][2], AllInfo[i][3], AllInfo[i][4], AllInfo[i][5]);
//            problemRepository.save(problem);

            problemRequestDto.setProblemTitle(AllInfo[i][1]);
            problemRequestDto.setFinishedCount(AllInfo[i][2]);
            problemRequestDto.setProblemSource(AllInfo[i][3]);
            problemRequestDto.setProblemLevel(AllInfo[i][5]);
            Long id = service.create(problemRequestDto);

            String[] strAry = AllInfo[i][4].split(" ");
            for (String s : strAry) {
                programmingLanguageRequestDto.setLanguage(s);
                programmingLanguageRequestDto.setProblemId(id);
                programmingLanguageService.create(programmingLanguageRequestDto);
            }

        }
    }

    @GetMapping("/")
    public List<Problem> AllProblem() {
        return problemRepository.findAll();
    }
}
