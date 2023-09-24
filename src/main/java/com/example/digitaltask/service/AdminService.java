package com.example.digitaltask.service;


import com.example.digitaltask.model.dto.AdminDto;
import com.example.digitaltask.model.dto.RoleDto;
import com.example.digitaltask.model.dto.UpdatePasswordDto;

public interface AdminService {

    AdminDto getUserById(Long id);

    void deleteAllUsers();

    void deleteUserById(Long id);

    void updateAccount(AdminDto adminDto);

    void updatePassword(UpdatePasswordDto updatePasswordDto);

    void updateUserRole(String username, RoleDto roleDto);
}