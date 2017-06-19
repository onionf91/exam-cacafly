package com.example.demo.repository;

import com.example.demo.domain.NativeAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NativeAdRepo extends JpaRepository<NativeAd, Long> {
}
