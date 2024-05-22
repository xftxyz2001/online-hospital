package com.nwu.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    String upload(MultipartFile file) throws IOException;
}
