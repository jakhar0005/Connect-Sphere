package com.connectSphere.uploaderService.service;

import com.connectSphere.uploaderService.config.UploaderConfig;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleCLoudStorageUploaderService implements UploaderService{
    private final Storage storage;

    @Value("${gcloud.storage-bucket-name}")
    private String bucketName;

    @Override
    public String upload(MultipartFile file) {
        final var fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        final var blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();
        try {
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}
