package com.example.digitaltask.dao.repo;

import com.example.digitaltask.dao.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    Optional<ImageEntity> findByName(String fileName);
    Optional<ImageEntity> deleteByName(String fileName);

}