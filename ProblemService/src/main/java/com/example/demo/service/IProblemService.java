package com.example.demo.service;

import com.example.demo.repository.ProblemRepository;
import org.springframework.stereotype.Service;

@Service
public interface IProblemService {
    createProblem() ;
    updateProblem() ;
    deleteProblem() ;
    fetchProblemById() ;
    filterProblems() ;
}
