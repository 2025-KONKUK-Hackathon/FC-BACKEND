package com.example.FC_BACKEND.domain.meeting.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MeetingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @Column(nullable = false)
    private String url;

    public static MeetingImage create(Meeting meeting, String url) {
        return MeetingImage.builder()
                .meeting(meeting)
                .url(url)
                .build();
    }
}
