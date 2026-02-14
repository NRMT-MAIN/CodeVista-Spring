package com.example.demo.mapper;

import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.dtos.TestCaseDTO;
import com.example.demo.models.Problem;
import com.example.demo.models.TestCase;

import java.util.List;

public class ProblemMapper {
    public Problem mapToProblemEntity(CreateProblemRequestDTO dto) {
        return Problem.builder()
                .slug(dto.getSlug())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .constraints(dto.getConstraints())
                .difficulty(dto.getDifficulty())
                .status(dto.getStatus())
                .build();
    }

    public CreateProblemResponseDTO mapToProblemResponse(Problem entity) {

        List<TestCaseDTO> sampleTestCases =
                entity.getTestCases().stream()
                        .filter(TestCase::getIsSample)
                        .map(tc -> TestCaseDTO.builder()
                                .input(tc.getInput())
                                .expectedOutput(tc.getExpectedOutput())
                                .isSample(true)
                                .build())
                        .toList();

        return CreateProblemResponseDTO.builder()
                .id(entity.getId())
                .slug(entity.getSlug())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .constraints(entity.getConstraints())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .testCases(sampleTestCases)
                .build();
    }
}
