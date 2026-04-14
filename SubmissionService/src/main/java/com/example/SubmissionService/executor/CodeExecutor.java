package com.example.SubmissionService.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CodeExecutor {

    public String execute(String code, String language, String input) {

        try {
            Path tempDir = Files.createTempDirectory("code");
            Path file = tempDir.resolve("Main.java");

            Files.writeString(file, code);

            // Compile
            Process compile = new ProcessBuilder("javac", file.toString())
                    .directory(tempDir.toFile())
                    .redirectErrorStream(true)
                    .start();
            log.info("Compiling code..." + compile.info().commandLine().orElse("Unknown command"));

            if (!compile.waitFor(5, TimeUnit.SECONDS)) {
                return "ERROR";
            }
            log.info("Compilation finished with exit code: " + compile.exitValue());
            // Run
            Process run = new ProcessBuilder("java", "-cp", tempDir.toString(), "Main")
                    .start();

            log.info("Running code...");
            // Input
            try (BufferedWriter writer =
                         new BufferedWriter(new OutputStreamWriter(run.getOutputStream()))) {
                writer.write(input);
                writer.flush();
            }
            log.info("Input provided to code.");
            // Timeout protection
            if (!run.waitFor(2, TimeUnit.SECONDS)) {
                run.destroy();
                return "TLE";
            }

            log.info("Execution finished with exit code: " + run.exitValue());
            BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));

            return reader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            return "ERROR";
        }
    }
}