package org.example.scheduleadvanced.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {
    @NotNull
    private Long memberId;

    @NotNull
    private Long scheduleId;

    @NotNull
    @Size(max = 100, message = "내용은 100자 이내로 입력해주세요.")
    private String content;
}
