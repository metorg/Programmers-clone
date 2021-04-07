package com.sparta.programmersclone.Service;

import com.sparta.programmersclone.dto.ProblemRequestDto;
import com.sparta.programmersclone.entity.Problem;
import com.sparta.programmersclone.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public Long create(ProblemRequestDto requestDto) {
        Problem newProblem = new Problem(requestDto);
        problemRepository.save(newProblem);
        return newProblem.getId();
    }

    public Problem findById(Long id) {
        return problemRepository.findById(id).orElseThrow(() -> new NullPointerException());
    }
}
