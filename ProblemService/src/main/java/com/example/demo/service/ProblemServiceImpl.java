package com.example.demo.service;

import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.ProblemTagRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements IProblemService{

    private final ProblemRepository problemRepo ;
    private final TagRepository tagRepo ;
    private final TestCaseRepository testCaseRepo ;
    private final ProblemTagRepository problemTagRepo ;

    @Override
    public CreateProblemResponseDTO createProblem(CreateProblemRequestDTO problemRequestDTO) {
        return null;
    }

    @Override
    public CreateProblemResponseDTO fetchProblemById(Long id) {
        return null;
    }

    @Override
    public CreateProblemResponseDTO updateProblem(Long id , CreateProblemRequestDTO problemRequestDTO) {
        return null;
    }

    @Override
    public String deleteProblem(Long id) {
        return "";
    }

    @Override
    public List<String> filterProblems(String difficulty , String tags) {
        return List.of();
    }
}
