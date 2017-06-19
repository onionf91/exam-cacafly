package com.example.demo.repository;

import com.example.demo.domain.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepo extends JpaRepository<Description, Long> {
}
