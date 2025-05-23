package org.example.scheduleadvanced.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MemberModifyRequestDto {
    @Setter
    private Long id;
    private final String email;
    private final String nickname;
    private final String password;

    private final String oldEmail;
    private final String oldNickname;
    private final String oldPassword;

    public boolean isEmailUpdate(){
        return email != null && oldEmail != null;
    }

    public boolean isNicknameUpdate(){
        return nickname != null & oldNickname != null;
    }

    public boolean isPasswordUpdate(){
        return password != null && oldPassword != null;
    }
}
