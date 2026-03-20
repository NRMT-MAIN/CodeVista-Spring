package com.example.demo.service;

import com.example.demo.models.TestCase;
import com.example.demo.repository.ProblemRepository;
import com.example.demo.repository.TestCaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestCaseServiceImpl implements ITestCaseService {
    private final ProblemRepository problemRepo ;
    private final TestCaseRepository testCaseRepo ;

    @Override
    public List<TestCase> fetchTestCaseByProblemId(Long id) {
        if(!problemRepo.existsById(id)) {
            log.warn("Problem doesn't exists by id : " + id);
            throw new RuntimeException("Problem doesn't exists by id : " + id) ;
        }

        List<TestCase> tc = testCaseRepo.findByProblemId(id) ;
        log.info(tc.size() + " Test cases fetched for problem id : " + id);
        return tc ;
    }
}
