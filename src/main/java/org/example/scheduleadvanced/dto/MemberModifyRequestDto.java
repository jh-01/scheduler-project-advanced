package org.example.scheduleadvanced.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
public class MemberModifyRequestDto {
    @Setter
    @NotNull
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "유효한 이메일 주소를 입력하세요.")
    private final String email;

    private final String nickname;

    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$",
            message = "비밀번호는 8~20자이며, 영문자, 숫자, 특수문자를 포함해야 합니다."
    )
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
