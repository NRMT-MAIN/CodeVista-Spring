package com.example.SubmissionService.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Bean
    public AmazonS3 amazonS3() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("your-access-key", "your-secret-key");
        return AmazonS3ClientBuilder.standard()
                .withRegion("ap-south-1") // replace with your region
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }
}
