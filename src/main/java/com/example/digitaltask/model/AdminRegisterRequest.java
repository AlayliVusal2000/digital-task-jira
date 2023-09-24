package com.example.digitaltask.model;

import com.example.digitaltask.annotation.ValidPassword;
import com.example.digitaltask.model.constns.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminRegisterRequest {


    @NotBlank(message = "The name part must be filled!")
    String name;
    @NotBlank(message = "The surname part must be filled!")
    String surname;
    @Size(min = 5,max = 15,message = "Record in the range shown: 5-15")
    String username;
    @NotNull(message = "JobTitle cannot be null")
    String jobTitle;
    @ValidPassword
    String password;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Choose one from ADMIN, USER")
    Role role=Role.ADMIN;
}
