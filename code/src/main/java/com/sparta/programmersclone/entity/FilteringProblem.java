package com.sparta.programmersclone.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class FilteringProblem {

    public FilteringProblem(Problem problem) {
        this.problem = problem;
    }

    // ID가 자동으로 생성 및 증가
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Problem problem;
}
