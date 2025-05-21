package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleadvanced.entity.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private final LocalDateTime createAt;

    private final LocalDateTime updatedAt;

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
