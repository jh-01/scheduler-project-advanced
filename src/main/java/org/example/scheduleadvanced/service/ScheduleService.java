package org.example.scheduleadvanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.ScheduleResponseDto;
import org.example.scheduleadvanced.entity.Schedule;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.exception.UnauthorizedException;
import org.example.scheduleadvanced.repository.ScheduleRepository;
import org.example.scheduleadvanced.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto createSchedule(String title, String content, long memberId){
        Schedule schedule = new Schedule(title, content);
        Optional<Member> optionalUser = memberRepository.findById(memberId);
        Member member = optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        schedule.setMember(member);
        scheduleRepository.save(schedule);
        return ScheduleResponseDto.toDto(schedule);
    }

    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public List<ScheduleResponseDto> getAllSchedulesById(Long userId) {

        return scheduleRepository.findAllByMemberId(userId)
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto getScheduleById(Long id){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정 없음");
        return ScheduleResponseDto.toDto(optionalSchedule.get());
    }

    @Transactional
    public ScheduleResponseDto modifySchedule(Long id, String title, String content, Long memberId){
        // 자신의 댓글인지 확인
        Schedule schedule = scheduleRepository.findScheduleById(id);
        if(!schedule.getMember().getId().equals(memberId))
            throw new UnauthorizedException("자신의 일정만 수정할 수 있습니다.");

        schedule.updateSchedule(title, content);
        return ScheduleResponseDto.toDto(schedule);
    }

    public void deleteSchedule(Long id, Long memberId){
        // 자신의 댓글인지 확인
        Schedule schedule = scheduleRepository.findScheduleById(id);
        if(!schedule.getMember().getId().equals(memberId))
            throw new UnauthorizedException("자신의 일정만 삭제할 수 있습니다.");

        scheduleRepository.delete(scheduleRepository.findScheduleById(id));
    }
}
