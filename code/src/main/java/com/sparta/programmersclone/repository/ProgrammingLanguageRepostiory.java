package com.sparta.programmersclone.repository;

import com.sparta.programmersclone.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLanguageRepostiory extends JpaRepository<ProgrammingLanguage, Long> {
}
