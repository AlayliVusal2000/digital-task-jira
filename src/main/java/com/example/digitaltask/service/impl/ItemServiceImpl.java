package com.example.digitaltask.service.impl;

import com.example.digitaltask.dao.entity.ItemEntity;
import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.dao.repo.ItemRepository;
import com.example.digitaltask.dao.repo.UserRepository;
import com.example.digitaltask.exception.ItemNotFoundException;
import com.example.digitaltask.exception.UserNotFoundException;
import com.example.digitaltask.model.dto.ItemDto;
import com.example.digitaltask.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemEntity create(ItemEntity item, Long id) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        item.setName(item.getName());
        item.setDescription(item.getDescription());
        item.setItems(userEntity);
        return itemRepository.save(item);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        itemRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        itemRepository.deleteAll();
    }

    @Override
    public void getById(Long id) {
        itemRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        itemRepository.findById(id);

    }

    @Override
    public void getAll() {
        itemRepository.findAll();

    }

    @Override
    public void updateItem(Long id, ItemDto itemDto) {
        ItemEntity item = itemRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());

        itemRepository.save(item);
        log.info("Item updated");

    }
}
