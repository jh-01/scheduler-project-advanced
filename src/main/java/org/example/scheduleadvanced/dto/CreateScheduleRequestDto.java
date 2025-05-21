package org.example.scheduleadvanced.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {
    @Id
    @Column
    private final String title;

    private final String content;

    private final Long userId;
}
