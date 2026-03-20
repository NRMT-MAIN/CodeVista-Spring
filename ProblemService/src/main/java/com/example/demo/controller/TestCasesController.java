package com.example.demo.controller;

import com.example.demo.service.ITestCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/testcases")
@RequiredArgsConstructor
public class TestCasesController {
    private final ITestCaseService testCaseService ;

    @GetMapping("/{problemId}")
    public ResponseEntity<?> getTestCasesByProblemId(Long problemId) {
        return ResponseEntity.ok(testCaseService.fetchTestCaseByProblemId(problemId)) ;
    }
}
