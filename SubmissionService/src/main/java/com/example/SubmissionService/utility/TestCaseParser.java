package com.example.SubmissionService.utility;

import com.example.SubmissionService.dtos.TestCaseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCaseParser {
    private static final String DELIMITER = "###";

    public List<TestCaseDTO> parse(String fullInput, String fullOutput) {

        String[] inputs = fullInput.split(DELIMITER);
        String[] outputs = fullOutput.split(DELIMITER);

        if (inputs.length != outputs.length) {
            throw new RuntimeException("Mismatch between input and output test cases");
        }

        List<TestCaseDTO> testCases = new ArrayList<>();

        for (int i = 0; i < inputs.length; i++) {
            testCases.add(new TestCaseDTO(inputs[i].trim(), outputs[i].trim()));
        }

        return testCases;
    }
}