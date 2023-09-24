package com.example.digitaltask.controller;

import com.example.digitaltask.model.dto.UpdatePasswordDto;
import com.example.digitaltask.model.dto.UserDto;
import com.example.digitaltask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getMyAccount")
    public ResponseEntity<UserDto> getAccount() {
        return ResponseEntity.ok(userService.getMyAccount());
    }

    @DeleteMapping("/deleteMyAccount")
    public void deleteUserById() {
        userService.deleteMyAccount();
    }


    @PutMapping("/updateMyAccount")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateMyAccount(userDto);

    }

    @PutMapping("/updateMyPassword")
    public void updatePassword(@RequestBody @Valid UpdatePasswordDto updatePasswordDto) {
        userService.updateMyPassword(updatePasswordDto);
    }


}
