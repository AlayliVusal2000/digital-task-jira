package com.example.digitaltask.controller;

import com.example.digitaltask.model.dto.AdminDto;
import com.example.digitaltask.model.dto.RoleDto;
import com.example.digitaltask.model.dto.UpdatePasswordDto;
import com.example.digitaltask.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<AdminDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUserById(@PathVariable Long id) {
        adminService.deleteUserById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllUser() {
        adminService.deleteAllUsers();
    }

    @PutMapping("/updateAccount")
    public void updateAccount(@Valid @RequestBody AdminDto adminDto) {
        adminService.updateAccount(adminDto);

    }

    @PutMapping("/updatePassword")
    public void updatePassword(@Validated
                               @RequestBody UpdatePasswordDto updatePasswordDto) {
        adminService.updatePassword(updatePasswordDto);
    }

    @PostMapping("/updateRole/{username}")
    public void updateRole(@PathVariable String username, @RequestBody RoleDto roleDto) {
        adminService.updateUserRole(username, roleDto);
    }
}
