package com.example.demo.service;

import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.dtos.FilterProblemResponse;
import com.example.demo.models.Difficulty;
import com.example.demo.models.Problem;
import com.example.demo.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProblemService {
    public CreateProblemResponseDTO createProblem(CreateProblemRequestDTO problemRequestDTO) ;
    public CreateProblemResponseDTO updateProblem(Long id , CreateProblemRequestDTO problemRequestDTO) throws Exception;
    public String deleteProblem(Long id) throws Exception;
    public CreateProblemResponseDTO fetchProblemById(Long id) throws Exception;
    public List<FilterProblemResponse> filterProblems(Difficulty difficulty , String tags) ;
}
