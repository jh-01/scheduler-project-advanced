package org.example.scheduleadvanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.CommentResponseDto;
import org.example.scheduleadvanced.entity.Comment;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.entity.Schedule;
import org.example.scheduleadvanced.exception.UnauthorizedException;
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
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = optionalSchedule.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다."));

        Member member = memberRepository.findMemberById(memberId);
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
    public CommentResponseDto modifyComment(long id, String content, Long memberId){
        // 해당 댓글 존재하는지 찾기
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다.");

        // 댓글을 작성한 사람이 맞는지 확인
        Comment comment = optionalComment.get();
        if(!comment.getMember().getId().equals(memberId))
            throw new UnauthorizedException("다른 유저의 댓글을 수정할 수 없습니다!");

        comment.updateContent(content);
        return CommentResponseDto.toDto(comment);
    }

    public void deleteComment(long id, Long memberId){
        // 해당 댓글 존재하는지 찾기
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if(optionalComment.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 댓글이 존재하지 않습니다.");

        // 댓글을 작성한 사람이 맞는지 확인
        Comment comment = optionalComment.get();
        if(!comment.getMember().getId().equals(memberId))
            throw new UnauthorizedException("다른 유저의 댓글을 삭제할 수 없습니다!");

        commentRepository.delete(commentRepository.findCommentById(id));
    }
}