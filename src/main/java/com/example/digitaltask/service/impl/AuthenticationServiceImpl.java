package com.example.digitaltask.service.impl;

import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.dao.repo.UserRepository;
import com.example.digitaltask.exception.UserAlreadyRegisteredException;
import com.example.digitaltask.model.*;
import com.example.digitaltask.service.AuthenticationService;
import com.example.digitaltask.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse adminRegister(AdminRegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException("This user already exists.");

        }
        UserEntity user = UserEntity
                .builder()
                .name(request.getName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .jobTitle(request.getJobTitle())
                .role(request.getRole())
                .build();
        userRepository.save(user);
        return RegisterResponse.buildRegisterDto(user);

    }

    @Override
    public RegisterResponse useRegister(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException("This user already exists.");
        }
        UserEntity user = UserEntity
                .builder()
                .name(userRegisterRequest.getName())
                .surname(userRegisterRequest.getSurname())
                .username(userRegisterRequest.getUsername())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .jobTitle(userRegisterRequest.getJobTitle())
                .role(userRegisterRequest.getRole())
                .build();
        userRepository.save(user);
        return RegisterResponse.buildRegisterDto(user);
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));
        UserEntity userEntity = userRepository
                .findByUsername(request.getUsername()).orElseThrow();
        String jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

}