package com.sparta.programmersclone.repository;

import com.sparta.programmersclone.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
