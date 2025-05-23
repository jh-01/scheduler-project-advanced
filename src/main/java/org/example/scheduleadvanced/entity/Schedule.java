package org.example.scheduleadvanced.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "schedule")
@Getter
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 설정
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    public Schedule(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void updateSchedule(String title, String content){
        this.title = title;
        this.content = content;
    }
}