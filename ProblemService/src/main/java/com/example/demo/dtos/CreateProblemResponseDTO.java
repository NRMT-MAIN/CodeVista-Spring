package com.example.demo.dtos;

import com.example.demo.models.Difficulty;
import com.example.demo.models.ProblemTag;
import com.example.demo.models.TestCase;

import java.time.LocalDateTime;
import java.util.List;

public class CreateProblemResponseDTO {
    private String slug ;
    private String title ;
    private Difficulty difficulty ;
    private String description ;
    private String constraints ;
    private String status ;
    private List<ProblemTag> problemTags ;
    private List<TestCase> testCases ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt ;
}
