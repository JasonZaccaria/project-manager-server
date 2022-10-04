package com.projectmanager.projectmanagerproject.files;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    private final FileConfig fileConfig;
    private final FilesServiceImpl filesServiceImpl;

    @Value("${s3.bucketname}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    //@Bean
    public Optional<FileUploadRes> uploadFile(MultipartFile file) throws IOException {
        try {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        PutObjectResult putObjectResult = s3Client.putObject(new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(file.getBytes()), objectMetadata));
        Optional<FileUploadRes> fileUploadRes = Optional
                .of(new FileUploadRes(fileName, s3Client.getUrl(bucketName, fileName).toString()));
        return fileUploadRes;
    } catch (Exception exception) {
        System.out.println(exception);
        Optional<FileUploadRes> fileUploadResFailure = Optional.empty();
        return fileUploadResFailure;
        }
    }
    
}