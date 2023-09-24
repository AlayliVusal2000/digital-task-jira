package com.example.digitaltask.model.dto;

import com.example.digitaltask.annotation.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePasswordDto {
    String oldPassword;
    @ValidPassword
    String newPassword;
    @ValidPassword
    String newPasswordAgain;
}
