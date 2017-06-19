package com.example.demo.repository;

import com.example.demo.domain.NativeAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NativeAdRepo extends JpaRepository<NativeAd, Long> {

    Optional<NativeAd> findByTitle(String title);
}
