package com.example.digitaltask.controller;

import com.example.digitaltask.dao.entity.ItemEntity;
import com.example.digitaltask.model.dto.ItemDto;
import com.example.digitaltask.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static com.example.digitaltask.model.mapper.CustomMapper.INSTANCE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/create/{id}")
    public ItemEntity create(@PathVariable Long id,@RequestBody ItemEntity item) {
       return itemService.create(item,id);
    }
    @DeleteMapping("/deleteById/{id}")
    void deleteById(@PathVariable Long id){
        itemService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    void deleteAll(){
        itemService.deleteAll();
    }
    @GetMapping("/getById/{id}")
    void getById(@PathVariable Long id){
        itemService.getById(id);
    }
    @GetMapping("/getAll")
    void getAll(){
        itemService.getAll();
    }

    @PutMapping("/update/{id}")
    void updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto){
        itemService.updateItem(id,itemDto);
    }


}
