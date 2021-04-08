package com.sparta.programmersclone.controller;

import com.sparta.programmersclone.Service.ProblemService;
import com.sparta.programmersclone.Service.ProgrammingLanguageService;
import com.sparta.programmersclone.crawling.SeleniumCrawling;
import com.sparta.programmersclone.dto.ProblemRequestDto;
import com.sparta.programmersclone.dto.ProgrammingLanguageRequestDto;
import com.sparta.programmersclone.entity.*;
import com.sparta.programmersclone.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemRepository problemRepository;
    private final ProgrammingLanguageRepostiory programmingLanguageRepostiory;
    private final ProblemService problemService;
    private final ProgrammingLanguageService programmingLanguageService;
    private final FilterLevelRepository filterLevelRepository;
    private final FilterLanguageRepository filterLanguageRepository;
    private final FilterReferenceRepository filterReferenceRepository;

    @GetMapping("/")
    public List<Problem> AllProblem() {

        return problemRepository.findAll();
    }

    @GetMapping("/save")
    public void createProblem() throws InterruptedException {
        System.out.println("----------------확인------------------");
        ProblemRequestDto problemRequestDto = new ProblemRequestDto();
        ProgrammingLanguageRequestDto programmingLanguageRequestDto = new ProgrammingLanguageRequestDto();
        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        String[][] AllInfo = seleniumCrawling.activateBot();

        for (int i = 1; i < AllInfo.length; i++) {

            problemRequestDto.setProblemTitle(AllInfo[i][1]);
            problemRequestDto.setFinishedCount(AllInfo[i][2]);
            problemRequestDto.setProblemSource(AllInfo[i][3]);
            AllInfo[i][4] = AllInfo[i][4].trim();
            problemRequestDto.setProblemLanguage(AllInfo[i][4]);
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

    @GetMapping("/filter/level")
    public List<FilterLevel> createFilterLevel() {
        return filterLevelRepository.findAll();
    }

    @GetMapping("/filter/language")
    public List<FilterLanguage> createFilterLanguage() {
        return filterLanguageRepository.findAll();
    }

    @GetMapping("/filter/reference")
    public List<FilterReference> createFilterReference() {
        return filterReferenceRepository.findAll();
    }

    @GetMapping("/filter")
    public List<Problem> filterlingProblem(
            @RequestParam(required = false, value = "level") String[] level,
            @RequestParam(required = false, value = "language") String[] language,
            @RequestParam(required = false, value = "reference") String[] reference
    ) {
        int[] visit = new int[2000];
        Long problemId;

        // 선택한 분류들의 카운트
        int selectCount = 0;
        if (level != null) selectCount++;
        if (language != null) selectCount += language.length;
        if (reference != null) selectCount++;

        List<Problem> result = null;
        if (level != null) {
            List<Problem> tmp = null;
            result = problemRepository.findByProblemLevel(level[0]);

            for (int i = 0; i < result.size(); i++) {
                problemId = result.get(i).getId();
                visit[Math.toIntExact(problemId)]++;
            }

            // 한 카테고리에서 다중 선택시 리스트에 추가해줌(OR 연산 느낌)
            for (int i = 1; i < level.length; i++) {
//                System.out.println(level[i]);
                tmp = problemRepository.findByProblemLevel(level[i]);
                for (int j = 0; j < tmp.size(); j++) {
                    problemId = tmp.get(j).getId();
                    visit[Math.toIntExact(problemId)]++;
                }
            }
        }


        if (reference != null) {
            List<Problem> tmp = null;
            result = problemRepository.findByProblemSource(reference[0]);

            for (int i = 0; i < result.size(); i++) {
                problemId = result.get(i).getId();
                visit[Math.toIntExact(problemId)]++;
            }

            // 한 카테고리에서 다중 선택시 리스트에 추가해줌(OR 연산 느낌)
            for (int i = 1; i < reference.length; i++) {
                tmp = problemRepository.findByProblemSource(reference[i]);
                for (int j = 0; j < tmp.size(); j++) {
                    problemId = tmp.get(j).getId();
                    visit[Math.toIntExact(problemId)]++;
                }
            }
        }


        if (language != null) {
            for (int i = 0; i < language.length; i++) {
                List<ProgrammingLanguage> programmingLanguages = programmingLanguageRepostiory.findByLanguage(language[i]);
                problemId = programmingLanguages.get(0).getProblem().getId();
                visit[Math.toIntExact(problemId)]++;
                result = new ArrayList(Arrays.asList(problemService.findById(problemId)));

                for (int j = 1; j < programmingLanguages.size(); j++) {
                    problemId = programmingLanguages.get(j).getProblem().getId();
                    visit[Math.toIntExact(problemId)]++;
                    result.add(problemService.findById(problemId));
                }
            }
        }

        result = null;
        int cnt = 0;
        System.out.println("---------------------------------------------");
        boolean flag = false;
        for (int i = 1; i < 2000; i++) {
            if (visit[i] >= selectCount) {
                if (!flag) {
                    result = new ArrayList(Arrays.asList(problemService.findById((long) i)));
                    flag = true;
                    System.out.println(i + " : " + visit[i]);
                    cnt++;
                    continue;
                }
                System.out.println(i + " : " + visit[i]);
                result.add(problemService.findById((long) i));
                cnt++;
            }
        }
        System.out.println(cnt);

        return result;
    }
}

