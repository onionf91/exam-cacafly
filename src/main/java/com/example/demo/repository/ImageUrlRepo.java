package com.example.demo.repository;

import com.example.demo.domain.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageUrlRepo extends JpaRepository<ImageUrl, Long> {

    List<ImageUrl> findByNativeAdId(Long id);
}
