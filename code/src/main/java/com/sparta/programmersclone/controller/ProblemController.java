package com.sparta.programmersclone.controller;

import com.sparta.programmersclone.Service.ProblemService;
import com.sparta.programmersclone.Service.ProgrammingLanguageService;
import com.sparta.programmersclone.crawling.SeleniumCrawling;
import com.sparta.programmersclone.dto.ProblemRequestDto;
import com.sparta.programmersclone.dto.ProgrammingLanguageRequestDto;
import com.sparta.programmersclone.entity.Problem;
import com.sparta.programmersclone.entity.ProgrammingLanguage;
import com.sparta.programmersclone.repository.ProblemRepository;
import com.sparta.programmersclone.repository.ProgrammingLanguageRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemRepository problemRepository;
    private final ProgrammingLanguageRepostiory programmingLanguageRepostiory;
    private final ProblemService problemService;
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
            Long id = problemService.create(problemRequestDto);

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

    @GetMapping("/filter")
    public List<Problem> filterlingProblem(
            @RequestParam(required = false, value = "level") String[] level,
            @RequestParam(required = false, value = "language") String[] language,
            @RequestParam(required = false, value = "reference") String[] reference
    ) throws UnsupportedEncodingException {
//        return problemService.filteringProblems(level, language, reference);
        List<Problem> result = null;
        if (level != null && reference == null) {
            List<Problem> tmp = null;
            result = problemRepository.findByProblemLevel(level[0]);
            // 한 카테고리에서 다중 선택시 리스트에 추가해줌(OR 연산 느낌)
            for (int i = 1; i < level.length; i++) {
                System.out.println(level[i]);
                tmp = problemRepository.findByProblemLevel(level[i]);
                result.addAll(tmp);
            }
        }
        if (level == null && reference != null) {
            result = problemRepository.findByProblemSource(reference[0]);
        }
        if (level != null && reference != null) {
            result = problemRepository.findByProblemLevelAndProblemSource(level[0], reference[0]);
        }

//        List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepostiory.findByLanguage(language[0]);
//
////        List<Problem> tmp = null;
//        Long problemId = programmingLanguages.get(0).getProblem().getId();
//        System.out.println(problemId);
//        result = new ArrayList(Arrays.asList(problemService.findById(problemId)));
//
//        for (int i = 1; i < programmingLanguages.size(); i++) {
//            problemId = programmingLanguages.get(i).getProblem().getId();
//            result.add(problemService.findById(problemId));
//            System.out.println(problemId);
//
//        }
        return result;

    }
}

