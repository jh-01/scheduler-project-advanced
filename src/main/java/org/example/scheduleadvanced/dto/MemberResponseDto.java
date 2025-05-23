package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleadvanced.entity.Member;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private final LocalDateTime createAt;

    private final LocalDateTime updatedAt;

    public static MemberResponseDto toDto(Member user){
        return new MemberResponseDto(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
