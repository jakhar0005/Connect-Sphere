package com.connectSphere.postService.client;

import com.connectSphere.postService.config.FeignMultipartConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

//If here url is present then it will just ignore name and path, because in kubernetes
// we are not using discovery service so we only need the url so in case of
// kubernetes name and path will get just ignore because of url.
// We have written UPLOADER_SERVICE_URI with a colon because it wont affect when we use feign client locally.
@FeignClient(name = "uploader-service", path = "/upload", url = "${UPLOADER_SERVICE_URI:}", configuration = FeignMultipartConfig.class)
public interface UploaderServiceClient {

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> upload(@RequestPart("file") MultipartFile file);
}