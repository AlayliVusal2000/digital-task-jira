package com.example.digitaltask.model.mapper;

import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.model.dto.AdminDto;
import com.example.digitaltask.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomMapper {


    CustomMapper INSTANCE = Mappers.getMapper(CustomMapper.class);

    AdminDto buildEntityToAdminDto(UserEntity userEntity);
    UserDto buildEntityToUserDto(UserEntity userEntity);



}
