package com.example.digitaltask.service.impl;

import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.dao.repo.UserRepository;
import com.example.digitaltask.exception.IncorrectPasswordException;
import com.example.digitaltask.exception.OldAndNewPasswordNotSameException;
import com.example.digitaltask.exception.PasswordNotMatchesException;
import com.example.digitaltask.exception.UserNotFoundException;
import com.example.digitaltask.model.dto.AdminDto;
import com.example.digitaltask.model.dto.RoleDto;
import com.example.digitaltask.model.dto.UpdatePasswordDto;
import com.example.digitaltask.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import static com.example.digitaltask.model.mapper.CustomMapper.INSTANCE;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AdminDto getUserById(Long id) {
        Optional<UserEntity> userEntity = Optional
                .ofNullable(userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found.")));
        return userEntity
                .map(INSTANCE::buildEntityToAdminDto)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found."));
        userRepository.deleteById(id);
        log.info("The user has been deleted id: " + id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("All users have been deleted.");
    }

    @Override
    public void updateAccount(AdminDto adminDto) {
        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(contextHolder.getName()).get();
        if (userRepository.findByUsername(contextHolder.getName()).isPresent()) {

            userEntity.setName(adminDto.getName());
            userEntity.setSurname(adminDto.getSurname());
            userEntity.setUsername(adminDto.getUsername());
            userEntity.setJobTitle(adminDto.getJobTitle());
            userEntity.setRole(adminDto.getRole());

            userRepository.save(userEntity);
        }
    }

    @Override
    public void updatePassword(UpdatePasswordDto updatePasswordDto) {

        Authentication contextHolder1 = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(contextHolder1.getName()).get();
        if (!Objects.equals(userEntity.getPassword(), updatePasswordDto.getOldPassword())) {
            throw new IncorrectPasswordException("The entered password is incorrect.");
        }
        if (!Objects.equals(updatePasswordDto.getNewPassword(), (updatePasswordDto.getNewPasswordAgain()))) {
            throw new PasswordNotMatchesException("The passwords you entered are not the same.");
        }
        if (userEntity.getPassword().equals(updatePasswordDto.getNewPassword())) {
            throw new OldAndNewPasswordNotSameException("The new password cannot be the same as the previous one.");
        }
        String encodedPassword = passwordEncoder.encode(updatePasswordDto.getNewPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        log.info("User password has been changed.");
    }

    @Override
    public void updateUserRole(String username, RoleDto roleDto) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found."));
        userEntity.setRole(roleDto.getNewRole());
        userRepository.save(userEntity);
    }


}

