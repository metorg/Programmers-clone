package com.sparta.programmersclone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Problem {

    public Problem(String problemTitle, String finishedCount, String problemSource, String problemLanguage) {
        this.problemTitle = problemTitle;
        this.finishedCount = finishedCount;
        this.problemSource = problemSource;
        this.problemLanguage = problemLanguage;
    }

    // ID가 자동으로 생성 및 증가
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 문제 제목
    @Column(nullable = false)
    private String problemTitle;

    // 문제 난이도
    @Column(nullable = false)
    private String finishedCount;

    // 문제 출처
    @Column(nullable = false)
    private String problemSource;

    // 지원 언어
    @Column(nullable = false)
    private String problemLanguage;
}
