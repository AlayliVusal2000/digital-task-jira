package com.example.digitaltask.service.impl;

import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.dao.repo.UserRepository;
import com.example.digitaltask.exception.IncorrectPasswordException;
import com.example.digitaltask.exception.OldAndNewPasswordNotSameException;
import com.example.digitaltask.exception.PasswordNotMatchesException;
import com.example.digitaltask.model.dto.AdminDto;
import com.example.digitaltask.model.dto.UpdatePasswordDto;
import com.example.digitaltask.model.dto.UserDto;
import com.example.digitaltask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.digitaltask.model.mapper.CustomMapper.INSTANCE;



@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Override
    public UserDto getMyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByUsername(authentication.getName()).get();
        return INSTANCE.buildEntityToUserDto(entity);
    }

    @Override
    public void deleteMyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByUsername(authentication.getName()).get();
        userRepository.delete(entity);
        log.info("Your account has been deleted.");
    }

    @Override
    public void updateMyAccount(UserDto userDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(auth.getName()).get();
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setJobTitle(userDto.getJobTitle());

        userRepository.save(userEntity);

        log.info("Your account information has been updated.");
    }


    @Override
    public void updateMyPassword(UpdatePasswordDto updatePasswordDto) {
        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(contextHolder.getName()).get();
        if (!userEntity.getPassword().equals(updatePasswordDto.getOldPassword())) {
            throw new IncorrectPasswordException("The entered password is incorrect.");
        }
        if (!updatePasswordDto.getNewPassword().equals(updatePasswordDto.getNewPasswordAgain())) {
            throw new PasswordNotMatchesException("The passwords you entered are not the same.");
        }
        if (userEntity.getPassword().equals(updatePasswordDto.getNewPassword())) {
            throw new OldAndNewPasswordNotSameException("The new password cannot be the same as the previous one.");
        }
        String encodedPassword = passwordEncoder.encode(updatePasswordDto.getNewPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        log.info("Your password has been updated.");
    }


}
