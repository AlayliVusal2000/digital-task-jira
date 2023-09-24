package com.example.digitaltask.service;

import com.example.digitaltask.model.dto.UpdatePasswordDto;
import com.example.digitaltask.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getMyAccount();

    void deleteMyAccount();

    void updateMyAccount(UserDto userDto);

    void updateMyPassword(UpdatePasswordDto updatePasswordDto);


}