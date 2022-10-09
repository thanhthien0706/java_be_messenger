package com.messenger.java_be_web_messenger.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.messenger.java_be_web_messenger.service.IStorageService;

@Service
public class StorageService implements IStorageService {

    @Autowired
    Cloudinary cloudinary;

    @Override
    public String storageFile(MultipartFile file) {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map<String, String> uploadParams = new HashMap<>();
            uploadParams.put("resource_type", "auto");
            uploadParams.put("folder", "web_messenger");
            Map uploadResult = cloudinary.uploader().uploadLarge(uploadedFile, uploadParams);
            boolean isDeleted = uploadedFile.delete();
            if (isDeleted) {
                System.out.println("File successfully deleted");
            } else
                System.out.println("File doesn't exist");
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> storageMultipletFiles(MultipartFile[] files) {
        ArrayList<String> listImgUrl = new ArrayList<String>();

        for (MultipartFile multipartFile : files) {
            String urlImg = storageFile(multipartFile);
            listImgUrl.add(urlImg);
        }
        return listImgUrl;
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String generatedFileName = UUID.randomUUID().toString().replace("_", "");
        generatedFileName = generatedFileName + "." + fileExtension;
        File convFile = new File(generatedFileName);

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
