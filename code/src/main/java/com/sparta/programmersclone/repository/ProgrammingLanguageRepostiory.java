package com.sparta.programmersclone.repository;

import com.sparta.programmersclone.entity.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgrammingLanguageRepostiory extends JpaRepository<ProgrammingLanguage, Long> {
    List<ProgrammingLanguage> findByLanguage(String language);
}
