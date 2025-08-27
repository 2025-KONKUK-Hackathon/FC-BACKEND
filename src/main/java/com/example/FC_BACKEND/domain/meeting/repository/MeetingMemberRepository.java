package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.entity.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {
    List<MeetingMember> findAllByMeetingId(Long meetingId);

    Optional<MeetingMember> findByMeetingIdAndUserId(Long meetingId, Long userId);
}
