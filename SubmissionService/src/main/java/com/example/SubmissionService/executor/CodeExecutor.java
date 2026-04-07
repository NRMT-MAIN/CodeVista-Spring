package com.example.SubmissionService.executor;

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
public class CodeExecutor {

    public String execute(String code, String language, String input) {

        try {
            Path tempDir = Files.createTempDirectory("code");
            Path file = tempDir.resolve("Main.java");

            Files.writeString(file, code);

            // Compile
            Process compile = new ProcessBuilder("javac", file.toString())
                    .directory(tempDir.toFile())
                    .start();

            if (!compile.waitFor(5, TimeUnit.SECONDS)) {
                return "ERROR";
            }

            // Run
            Process run = new ProcessBuilder("java", "-cp", tempDir.toString(), "Main")
                    .start();

            // Input
            try (BufferedWriter writer =
                         new BufferedWriter(new OutputStreamWriter(run.getOutputStream()))) {
                writer.write(input);
                writer.flush();
            }

            // Timeout protection
            if (!run.waitFor(5, TimeUnit.SECONDS)) {
                run.destroy();
                return "TLE";
            }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(run.getInputStream()));

            return reader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            return "ERROR";
        }
    }
}