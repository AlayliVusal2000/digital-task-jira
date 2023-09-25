package com.example.digitaltask.dao.repo;

import com.example.digitaltask.dao.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<ItemEntity,Long> {
}
