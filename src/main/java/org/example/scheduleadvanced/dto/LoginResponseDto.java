package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleadvanced.entity.Member;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private final Long id;
    private final String email;
    private final String nickname;

    public static LoginResponseDto toDto(Member user){
        return new LoginResponseDto(user.getId(), user.getEmail(), user.getNickname());
    }
}
