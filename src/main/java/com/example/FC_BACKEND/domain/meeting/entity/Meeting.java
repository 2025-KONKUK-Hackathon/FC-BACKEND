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

    @Column(nullable = false)
    LocalDate actualStartDate;

    @Column(nullable = false)
    LocalDate actualEndDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MeetingStatus meetingStatus = MeetingStatus.NOT_STARTED;

    public static Meeting createMeeting(String meetingName, String content, String category, int recruitNumber,
                                        LocalDate recruitStartDate, LocalDate recruitEndDate, LocalDate actualStartDate, LocalDate actualEndDate) {
        return Meeting.builder()
                .name(meetingName)
                .content(content)
                .category(Category.valueOf(category))
                .recruitNumber(recruitNumber)
                .recruitStartDate(recruitStartDate)
                .recruitEndDate(recruitEndDate)
                .actualStartDate(actualStartDate)
                .actualEndDate(actualEndDate)
                .build();
    }

}
