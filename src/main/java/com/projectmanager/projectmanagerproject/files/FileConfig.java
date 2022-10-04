package com.projectmanager.projectmanagerproject.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.Data;

@Configuration
@Data
public class FileConfig {
    @Value("${s3.accesskey}")
    private String accessKey;

    @Value("${s3.secretkey}")
    private String secretKey;

    @Value("${s3.region}")
    private String region;

    @Bean
    public AmazonS3 generateS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region).build();
    }
}
