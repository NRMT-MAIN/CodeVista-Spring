package com.example.demo.service;

import com.example.demo.models.TestCase;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.TagRepository;
import com.example.demo.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ITestCaseServiceImpl implements ITestCaseService {
    private final ProblemRepository problemRepo ;
    private final TestCaseRepository testCaseRepo ;

    @Override
    public List<TestCase> fetchTestCaseByProblemId(Long id) {
        if(!problemRepo.existsById(id)) {
            log.warn("Problem doesn't exists by id : " + id);
            throw new RuntimeException("Problem doesn't exists by id : " + id) ;
        }

        List<TestCase> tc = testCaseRepo.findByProblemId(id) ;
        return tc ;
    }
}
