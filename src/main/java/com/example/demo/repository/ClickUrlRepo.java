package com.example.demo.repository;

import com.example.demo.domain.ClickUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClickUrlRepo extends JpaRepository<ClickUrl, Long> {

    List<ClickUrl> findByNativeAdTitle(String title);
}
