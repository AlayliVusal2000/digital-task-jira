package com.example.digitaltask.model;

import com.example.digitaltask.dao.entity.UserEntity;
import com.example.digitaltask.model.constns.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {

    String name;
    String surname;
    String username;
    String password;
    String jobTitle;
    @Enumerated(EnumType.STRING)
    Role role;

    public static RegisterResponse buildRegisterDto(UserEntity userEntity){
        return RegisterResponse
                .builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .jobTitle(userEntity.getJobTitle())
                .role(userEntity.getRole())
                .build();


    }
}
