package com.example.digitaltask.service.impl;

import com.example.digitaltask.dao.entity.ImageEntity;
import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.dao.repo.ImageRepository;
import com.example.digitaltask.dao.repo.UserRepository;
import com.example.digitaltask.model.util.ImageUtil;
import com.example.digitaltask.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;
    private final UserRepository userRepository;

    public ImageEntity uploadImage(MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(authentication.getName()).get();

        ImageEntity image = repository.save(ImageEntity.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .image(userEntity)
                .build());
        return image;
    }

    public byte[] downloadImage(Long id /*String fileName*/) {
        Optional<ImageEntity> image = repository.findById(id);

        byte[] images = ImageUtil.decompressImage(image.get().getImageData());
        return images;
    }

    public void deleteImage(Long id) {
        repository.deleteById(id);

        if (repository.findById(id).isEmpty()) {
            log.error("This id not found: " + id);
        } else {
            repository.deleteById(id);
            log.info("Image deleted");
        }
    }
}
