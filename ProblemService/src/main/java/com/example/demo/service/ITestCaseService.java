package com.example.demo.service;

import com.example.demo.models.TestCase;

import java.util.List;

public interface ITestCaseService {
    List<TestCase> fetchTestCaseByProblemId(Long id) ;
}
