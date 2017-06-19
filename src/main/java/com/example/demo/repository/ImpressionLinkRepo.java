package com.example.demo.repository;

import com.example.demo.domain.ImpressionLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpressionLinkRepo extends JpaRepository<ImpressionLink, Long> {

    List<ImpressionLink> findByNativeAdTitle(String title);
}
