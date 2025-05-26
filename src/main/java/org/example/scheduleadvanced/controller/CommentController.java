package org.example.scheduleadvanced.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.*;
import org.example.scheduleadvanced.entity.Member;
import org.example.scheduleadvanced.exception.UnauthorizedException;
import org.example.scheduleadvanced.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> saveComment(
            @Validated @RequestBody CommentCreateRequestDto commentDto,
            HttpSession session
            ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        CommentResponseDto commentResponseDto = commentService.saveComment(commentDto.getContent(), commentDto.getScheduleId(), member.getId());
        return ResponseEntity.ok(commentResponseDto);
    }

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsOfSchedule(
            @NotNull @PathVariable Long scheduleId
    ){
        return ResponseEntity.ok(commentService.findAllCommentsOfSchedule(scheduleId));
    }

    @GetMapping("/members/{memberId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsOfMember(
            @NotNull @PathVariable Long memberId
    ){
        return ResponseEntity.ok(commentService.findAllCommentsOfMember(memberId));
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyComment(
            @PathVariable Long commentId,
            @Validated @RequestBody CommentModifyRequestDto commentModifyRequestDto,
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        CommentResponseDto commentResponseDto = commentService.modifyComment(commentId, commentModifyRequestDto.getContent(), member.getId());
        return ResponseEntity.ok(commentResponseDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            HttpSession session
    ){
        LoginResponseDto member = (LoginResponseDto) session.getAttribute("loginMember");
        if (member == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        commentService.deleteComment(commentId, member.getId());
        return ResponseEntity.ok("댓글 삭제를 완료했습니다.");
    }
}
