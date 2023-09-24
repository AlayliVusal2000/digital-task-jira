package com.example.digitaltask.service;

import com.example.digitaltask.model.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    RegisterResponse adminRegister(AdminRegisterRequest adminRequest);
    RegisterResponse useRegister(UserRegisterRequest userRegisterRequest);

}
