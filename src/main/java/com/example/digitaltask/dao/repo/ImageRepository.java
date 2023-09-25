package com.example.digitaltask.dao.repo;

import com.example.digitaltask.dao.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    Optional<ImageEntity> findByName(String fileName);
    Optional<ImageEntity> deleteByName(String fileName);

}