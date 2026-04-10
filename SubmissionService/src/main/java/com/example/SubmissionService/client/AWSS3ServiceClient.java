package com.example.SubmissionService.client;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AWSS3ServiceClient {
    private final AmazonS3Client s3Client;
    @Value("${BUCKET_NAME}")
    private final String bucketName ;

    public String getFileContent(String key) {
        GetObjectRequest request = new GetObjectRequest(bucketName , key) ;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(s3Client.getObject(request).getObjectContent()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file from S3", e);
        }
    }
}
