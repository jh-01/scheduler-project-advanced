package org.example.scheduleadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduleadvanced.entity.Comment;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long memberId;
    private Long scheduleId;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    public static CommentResponseDto toDto(Comment comment){
        return new CommentResponseDto(
                comment.getId(),
                comment.getMember().getId(),
                comment.getSchedule().getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
