package com.connectSphere.uploaderService.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploaderService {

    public String upload(MultipartFile file);
}
