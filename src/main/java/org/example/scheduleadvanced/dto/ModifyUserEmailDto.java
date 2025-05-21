package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyUserEmailDto {
    private final Long id;
    private final String oldEmail;
    private final String newEmail;
}
