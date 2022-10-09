package com.messenger.java_be_web_messenger.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    String storageFile(MultipartFile file);

    List<String> storageMultipletFiles(MultipartFile[] files);
}
