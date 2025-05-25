package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.LoginResponseDto;
import org.example.scheduleadvanced.dto.ScheduleCreateRequestDto;
import org.example.scheduleadvanced.dto.ScheduleModifyRequestDto;
import org.example.scheduleadvanced.dto.ScheduleResponseDto;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.exception.UnauthorizedException;
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
            @Validated @RequestBody ScheduleCreateRequestDto scheduleRequestDto,
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.createSchedule(
                        scheduleRequestDto.getTitle(),
                        scheduleRequestDto.getContent(),
                        member.getId()
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
            @Validated @RequestBody ScheduleModifyRequestDto modifyDto,
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        ScheduleResponseDto schedule = scheduleService.modifySchedule(id, modifyDto.getTitle(), modifyDto.getContent(), member.getId());
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @NotNull @PathVariable Long id,
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        scheduleService.deleteSchedule(id, member.getId());
        return ResponseEntity.ok("일정 삭제가 완료되었습니다.");
    }
}
