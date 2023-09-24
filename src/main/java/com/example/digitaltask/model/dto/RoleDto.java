package com.example.digitaltask.model.dto;

import com.example.digitaltask.model.constns.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    Role currentRole;
    Role newRole;
}
