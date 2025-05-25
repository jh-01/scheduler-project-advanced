package org.example.scheduleadvanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.CommentResponseDto;
import org.example.scheduleadvanced.dto.MemberResponseDto;
import org.example.scheduleadvanced.dto.ScheduleResponseDto;
import org.example.scheduleadvanced.entity.Comment;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.entity.Schedule;
import org.example.scheduleadvanced.repository.CommentRepository;
import org.example.scheduleadvanced.repository.MemberRepository;
import org.example.scheduleadvanced.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto saveComment(String content, Long scheduleId, Long memberId) {
        Optional<Member> optionalUser = memberRepository.findById(memberId);
        Member member = optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."));

        Comment comment = new Comment(content, member, schedule);
        commentRepository.save(comment);
        return CommentResponseDto.toDto(comment);
    }

    public List<CommentResponseDto> findAllCommentsOfSchedule(long scheduleId){
        return commentRepository.findAllByScheduleId(scheduleId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    public List<CommentResponseDto> findAllCommentsOfMember(long memberId){
        return commentRepository.findAllByMemberId(memberId)
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    @Transactional
    public CommentResponseDto modifyComment(long id, String content){
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다.");
        Comment comment = optionalComment.get();
        comment.updateContent(content);
        return CommentResponseDto.toDto(comment);
    }

    public void deleteComment(long id){
        commentRepository.delete(commentRepository.findCommentById(id));
    }
}