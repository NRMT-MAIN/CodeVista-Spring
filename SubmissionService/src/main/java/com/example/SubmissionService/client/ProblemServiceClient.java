package com.example.SubmissionService.client;

import com.example.SubmissionService.dtos.TestCaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "problem-service", url = "${problem.service.url}")
public interface ProblemServiceClient {
    @GetMapping("/testcases/{problemId}")
    List<TestCaseDTO> getTestCases(@PathVariable Long problemId);
}
