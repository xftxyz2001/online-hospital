package com.nwu.system.service.impl;

import com.aliyun.oss.OSS;
import com.nwu.system.autoconfigure.AliOSSProperties;
import com.nwu.system.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class IFileServiceImpl implements IFileService {

    private final OSS ossClient;
    private final AliOSSProperties aliOSSProperties;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String bucketName = aliOSSProperties.getBucketName();
        String endpoint = aliOSSProperties.getEndpoint();

        InputStream inputStream = file.getInputStream();

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            originalFileName = "";
        }
        String fileName = new StringBuffer(String.valueOf(new Date().getTime())).reverse().toString() + Math.random() + originalFileName.substring(originalFileName.lastIndexOf("."));

        ossClient.putObject(bucketName, fileName, inputStream);
        return getUrl(endpoint, bucketName, fileName);
    }

    private String getUrl(String endpoint, String bucketName, String fileName) {
        String[] split = endpoint.split("//");
        return split[0] + "//" + bucketName + "." + split[1] + "/" + fileName;
    }
}
