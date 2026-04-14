package com.example.SubmissionService.runners;

import com.example.SubmissionService.dtos.TestCaseDTO;
import com.example.SubmissionService.executor.CodeExecutor;
import com.example.SubmissionService.utility.TestCaseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmissionRunners implements CommandLineRunner {

    @Autowired
    private TestCaseParser testCaseParser;

    @Autowired
    private CodeExecutor executor;

    private final String submissionCode = """
        import java.util.Scanner;
        public class Main {
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += sc.nextInt();
                }
                System.out.println(sum);
            }
        }
        """;

    private final String input = """
        5
        1 2 3 4 5
        ###
        3
        10 20 30
        """;

    private final String output = """
        15
        ###
        60
        """;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Testing TestCaseParser...");

        // 1. Parse test cases
        List<TestCaseDTO> testCases = testCaseParser.parse(input, output);

        long startTime = System.currentTimeMillis();

        for (TestCaseDTO tc : testCases) {
            String result = executor.execute(
                    submissionCode,
                    "java",
                    tc.getInput()
            );

            System.out.println("Output: " + result);

            // Distinguish verdicts
            if (result.startsWith("Compilation Error")) {
                System.out.println("COMPILATION_ERROR");
                return;
            }
            if (result.startsWith("Runtime Error")) {
                System.out.println("RUNTIME_ERROR");
                return;
            }
            if ("TLE".equals(result)) {
                System.out.println("TIME_LIMIT_EXCEEDED");
                return;
            }
            if ("ERROR".equals(result)) {
                System.out.println("SYSTEM_ERROR");
                return;
            }

            // Compare with expected output
            if (!result.trim().equals(tc.getExpectedOutput().trim())) {
                System.out.println("WRONG_ANSWER");
                return;
            }
        }

        long runtime = System.currentTimeMillis() - startTime;
        System.out.println("ACCEPTED in " + runtime + " ms");
    }
}
