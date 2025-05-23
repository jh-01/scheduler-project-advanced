package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleModifyRequestDto {
    private final String title;

    private final String content;
}
