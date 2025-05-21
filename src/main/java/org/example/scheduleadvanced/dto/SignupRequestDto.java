package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {
    private String email;
    private String nickname;
    private String password;
}
