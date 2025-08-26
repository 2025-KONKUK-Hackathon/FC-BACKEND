package com.example.FC_BACKEND.domain.meeting.entity;

import com.example.FC_BACKEND.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_member_id")
    private Long id;

    private LocalDateTime registeredAt;

    @Builder.Default
    private boolean isHost = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    public static MeetingMember create(User user, Meeting meeting, LocalDateTime registeredAt) {
        return MeetingMember.builder()
                .user(user)
                .meeting(meeting)
                .registeredAt(registeredAt)
                .build();
    }
}
