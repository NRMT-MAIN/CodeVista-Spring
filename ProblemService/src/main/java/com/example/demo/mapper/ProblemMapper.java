package com.example.demo.mapper;

import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.dtos.FilterProblemResponse;
import com.example.demo.dtos.TestCaseDTO;
import com.example.demo.models.Problem;
import com.example.demo.models.TestCase;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProblemMapper {
    public static Problem mapToProblemEntity(CreateProblemRequestDTO dto) {

        Problem problem = Problem.builder()
                .slug(dto.getSlug())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .constraints(dto.getConstraints())
                .difficulty(dto.getDifficulty())
                .status(dto.getStatus())
                .build();

        if (dto.getTestCases() != null) {
            List<TestCase> testCases = dto.getTestCases().stream().map(tcDto -> {
                TestCase tc = new TestCase();

                tc.setInput(tcDto.getInput());
                tc.setExpectedOutput(tcDto.getExpectedOutput());
                tc.setIsSample(tcDto.getIsSample());

                tc.setProblem(problem);

                return tc;
            }).toList();

            problem.setTestCases(testCases);
        }

        return problem;
    }

    public static CreateProblemResponseDTO mapToProblemResponse(Problem entity) {

        List<TestCaseDTO> sampleTestCases = Optional.ofNullable(entity.getTestCases())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(tc -> Boolean.TRUE.equals(tc.getIsSample())) // ✅ SAFE
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

    public static FilterProblemResponse toFilterProblemResponseDTO(Problem problem) {
        return FilterProblemResponse.builder()
                .id(problem.getId())
                .slug(problem.getSlug())
                .title(problem.getTitle())
                .difficulty(problem.getDifficulty())
                .build();
    }
}
