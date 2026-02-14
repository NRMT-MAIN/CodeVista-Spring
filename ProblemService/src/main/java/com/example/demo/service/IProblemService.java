package com.example.demo.service;

import com.example.demo.dtos.CreateProblemRequestDTO;
import com.example.demo.dtos.CreateProblemResponseDTO;
import com.example.demo.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProblemService {
    public CreateProblemResponseDTO createProblem(CreateProblemRequestDTO problemRequestDTO) ;
    public CreateProblemResponseDTO updateProblem(Long id , CreateProblemRequestDTO problemRequestDTO) ;
    public String deleteProblem(Long id) ;
    public CreateProblemResponseDTO fetchProblemById(Long id) ;
    public List<String> filterProblems(String difficulty , String tags) ;
}
