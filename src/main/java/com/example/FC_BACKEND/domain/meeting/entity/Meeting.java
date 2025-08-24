package com.example.FC_BACKEND.domain.meeting.entity;

import com.example.FC_BACKEND.domain.meeting.constant.Category;
import com.example.FC_BACKEND.domain.meeting.constant.MeetingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int recruitNumber;

    @Column(nullable = false)
    LocalDate recruitStartDate;

    @Column(nullable = false)
    LocalDate recruitEndDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MeetingStatus meetingStatus = MeetingStatus.NOT_STARTED;

}
