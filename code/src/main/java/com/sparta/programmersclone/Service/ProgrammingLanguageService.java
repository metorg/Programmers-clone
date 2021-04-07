package com.sparta.programmersclone.Service;

import com.sparta.programmersclone.dto.ProgrammingLanguageRequestDto;
import com.sparta.programmersclone.entity.ProgrammingLanguage;
import com.sparta.programmersclone.repository.ProgrammingLanguageRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProgrammingLanguageService {

    private final ProgrammingLanguageRepostiory programmingLanguageRepository;

    private final ProblemService problemService;

    public ProgrammingLanguage create(ProgrammingLanguageRequestDto requestDto) {
        ProgrammingLanguage newLanguage = new ProgrammingLanguage(requestDto, problemService);
        return programmingLanguageRepository.save(newLanguage);
    }

}
