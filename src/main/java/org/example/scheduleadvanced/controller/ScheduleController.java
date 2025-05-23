package org.example.scheduleadvanced.controller;

import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.CreateScheduleRequestDto;
import org.example.scheduleadvanced.dto.ScheduleModifyRequestDto;
import org.example.scheduleadvanced.dto.ScheduleResponseDto;
import org.example.scheduleadvanced.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            // ServletRequest request,
            // @CookieValue(name = "memberId", required = true) Long memberId, // String->Long 자동 타입컨버팅
            @RequestBody CreateScheduleRequestDto scheduleRequestDto
            ) {
        // HttpServletRequest httpServletRequest = (HttpServletRequest) request;

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
            @PathVariable("id") Long id
    ){
        ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> modifySchedule(
            @PathVariable Long id,
            @RequestBody ScheduleModifyRequestDto modifyDto
            ){
        ScheduleResponseDto schedule = scheduleService.modifySchedule(id, modifyDto.getTitle(), modifyDto.getContent());
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id
    ){
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
