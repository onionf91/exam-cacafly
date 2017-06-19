package com.example.demo.repository;

import com.example.demo.domain.Description;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescriptionRepo extends JpaRepository<Description, Long> {

    List<Description> findByNativeAdTitle(String title);
}
