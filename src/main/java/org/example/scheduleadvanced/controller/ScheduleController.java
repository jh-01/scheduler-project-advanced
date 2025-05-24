package org.example.scheduleadvanced.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.ScheduleCreateRequestDto;
import org.example.scheduleadvanced.dto.ScheduleModifyRequestDto;
import org.example.scheduleadvanced.dto.ScheduleResponseDto;
import org.example.scheduleadvanced.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @Validated @RequestBody ScheduleCreateRequestDto scheduleRequestDto
            ) {
        ScheduleResponseDto scheduleResponseDto =
                scheduleService.createSchedule(
                        scheduleRequestDto.getTitle(),
                        scheduleRequestDto.getContent(),
                        scheduleRequestDto.getMemberId()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(
            @RequestParam(required = false) Long memberId
    ){
        List<ScheduleResponseDto> scheduleList =
                memberId == null?
                scheduleService.getAllSchedules():
                scheduleService.getAllSchedulesById(memberId);
        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(
            @NotNull @PathVariable("id") Long id
    ){
        ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> modifySchedule(
            @NotNull @PathVariable Long id,
            @Validated @RequestBody ScheduleModifyRequestDto modifyDto
            ){
        ScheduleResponseDto schedule = scheduleService.modifySchedule(id, modifyDto.getTitle(), modifyDto.getContent());
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @NotNull @PathVariable Long id
    ){
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
