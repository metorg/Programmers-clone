package com.sparta.programmersclone.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ProgrammingLanguageRequestDto {

    private String language;
    private Long ProblemId;
}
