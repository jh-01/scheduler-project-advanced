package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleadvanced.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String content;

    private final String nickname;

    private final LocalDateTime createAt;

    private final LocalDateTime updatedAt;

    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getUser().getNickname(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }
}
