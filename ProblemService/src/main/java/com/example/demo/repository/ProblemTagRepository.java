package com.example.demo.repository;

import com.example.demo.models.ProblemTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTagRepository extends JpaRepository<ProblemTag , Long> {
}
