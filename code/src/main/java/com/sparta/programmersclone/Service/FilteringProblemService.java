package com.sparta.programmersclone.Service;

import com.sparta.programmersclone.dto.ProblemRequestDto;
import com.sparta.programmersclone.entity.FilteringProblem;
import com.sparta.programmersclone.entity.Problem;
import com.sparta.programmersclone.repository.FilteringProblemRepository;
import com.sparta.programmersclone.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class FilteringProblemService {

    private final FilteringProblemRepository filteringProblemRepository;

//    public Long create(ProblemRequestDto requestDto) {
//        Problem newProblem = new Problem(requestDto);
//        problemRepository.save(newProblem);
//        return newProblem.getId();
//    }
//
//    public Problem findById(Long id) {
//        return problemRepository.findById(id).orElseThrow(() -> new NullPointerException());
//    }

    public Page<FilteringProblem> getProblems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return filteringProblemRepository.findAll(pageable);
    }
}
