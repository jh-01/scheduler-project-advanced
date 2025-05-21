package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyUserPasswordDto {
    private final long id;
    private final String oldPassword;
    private final String newPassword;
}
