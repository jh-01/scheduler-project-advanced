package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyUserNicknameDto {
    private final Long id;
    private final String oldNickname;
    private final String newNickname;
}
