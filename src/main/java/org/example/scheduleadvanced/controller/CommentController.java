package org.example.scheduleadvanced.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.scheduleadvanced.dto.CommentCreateRequestDto;
import org.example.scheduleadvanced.dto.CommentModifyRequestDto;
import org.example.scheduleadvanced.dto.CommentResponseDto;
import org.example.scheduleadvanced.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> saveComment(
            @Validated @RequestBody CommentCreateRequestDto commentDto
            ){
        CommentResponseDto commentResponseDto = commentService.saveComment(commentDto.getContent(), commentDto.getScheduleId(), commentDto.getMemberId());
        return ResponseEntity.ok(commentResponseDto);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsOfSchedule(
            @NotNull @PathVariable Long scheduleId
    ){
        return ResponseEntity.ok(commentService.findAllCommentsOfSchedule(scheduleId));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsOfMember(
            @NotNull @PathVariable Long memberId
    ){
        return ResponseEntity.ok(commentService.findAllCommentsOfMember(memberId));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyComment(
            @PathVariable Long commentId,
            @Validated @RequestBody CommentModifyRequestDto commentModifyRequestDto
    ){
        CommentResponseDto commentResponseDto = commentService.modifyComment(commentId, commentModifyRequestDto.getContent());
        return ResponseEntity.ok(commentResponseDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId
    ){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("댓글 삭제를 완료했습니다.");
    }
}
