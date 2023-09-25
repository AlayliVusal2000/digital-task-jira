package com.example.digitaltask.service;

import com.example.digitaltask.dao.entity.ItemEntity;
import com.example.digitaltask.model.dto.ItemDto;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    ItemEntity create(ItemEntity item,Long id);

    void deleteById(Long id);

    void deleteAll();

    void getById(Long id);

    void getAll();

    void updateItem(Long id, ItemDto itemDto);


}
