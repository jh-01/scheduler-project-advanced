package org.example.scheduleadvanced.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {
    @NotBlank(message = "할 일 제목은 필수입니다.")
    @Size(max = 10, message = "할 일 제목은 10자 이내로 입력해주세요.")
    private final String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 100, message = "내용은 100자 이내로 입력해주세요.")
    private final String content;

    @NotNull
    private final Long memberId;
}
