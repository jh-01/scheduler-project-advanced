package org.example.scheduleadvanced.repository;

import org.example.scheduleadvanced.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleId(Long schedule_id);
    List<Comment> findAllByMemberId(long memberId);
    Comment findCommentById(Long id);
}
