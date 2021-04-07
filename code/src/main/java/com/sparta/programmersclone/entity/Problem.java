package com.sparta.programmersclone.entity;

import com.sparta.programmersclone.dto.ProblemRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Problem {

    public Problem(ProblemRequestDto requestDto) {
        this.problemTitle = requestDto.getProblemTitle();
        this.problemLevel = requestDto.getProblemLevel();
        this.problemSource = requestDto.getProblemSource();
        this.problemLanguage = requestDto.getProblemLanguage();
        this.finishedCount = requestDto.getFinishedCount();
    }

    // ID가 자동으로 생성 및 증가
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 문제 제목
    @Column(nullable = false)
    private String problemTitle;

    // 푼 사람수
    @Column(nullable = false)
    private String finishedCount;

    // 문제 출처
    @Column(nullable = false)
    private String problemSource;

    // 제공 언어
    @Column(nullable = false)
    private String problemLanguage;

    // 문제 난이도
    @Column(nullable = false)
    private String problemLevel;
}
