package org.example.scheduleadvanced.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.CreateScheduleRequestDto;
import org.example.scheduleadvanced.dto.ModifyScheduleRequestDto;
import org.example.scheduleadvanced.dto.ScheduleResponseDto;
import org.example.scheduleadvanced.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            // ServletRequest request,
            // @CookieValue(name = "userId", required = true) Long userId, // String->Long 자동 타입컨버팅
            @RequestBody CreateScheduleRequestDto scheduleRequestDto
            ) throws IOException, ServletException {
        // HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.createSchedule(
                        scheduleRequestDto.getTitle(),
                        scheduleRequestDto.getContent(),
                        scheduleRequestDto.getUserId()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(){
        List<ScheduleResponseDto> scheduleList = scheduleService.getAllSchedules();
        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    @GetMapping("/all/{user_id}")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedulesById(
            @PathVariable("user_id") Long userId
    ){
        List<ScheduleResponseDto> scheduleList = scheduleService.getAllSchedulesById(userId);
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
            @RequestBody ModifyScheduleRequestDto modifyDto
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
