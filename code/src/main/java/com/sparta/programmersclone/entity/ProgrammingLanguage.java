package com.sparta.programmersclone.entity;

import com.sparta.programmersclone.Service.ProblemService;
import com.sparta.programmersclone.dto.ProgrammingLanguageRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class ProgrammingLanguage {

    // ID가 자동으로 생성 및 증가

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String language;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Problem_ID")
    private Problem problem;


    public ProgrammingLanguage(ProgrammingLanguageRequestDto requestDto, ProblemService problemService) {

        this.language = requestDto.getLanguage();
        this.problem = problemService.findById(requestDto.getProblemId());
    }

}