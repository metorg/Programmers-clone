package com.sparta.programmersclone.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Setter
public class ProblemRequestDto {

    private String problemTitle;
    private String finishedCount;
    private String problemSource;
    private String problemLevel;

}
