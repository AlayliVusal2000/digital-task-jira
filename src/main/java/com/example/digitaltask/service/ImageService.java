package com.example.digitaltask.service;

import com.example.digitaltask.dao.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageEntity uploadImage(MultipartFile file) throws IOException;

    byte[] downloadImage();

    void deleteImage();
}