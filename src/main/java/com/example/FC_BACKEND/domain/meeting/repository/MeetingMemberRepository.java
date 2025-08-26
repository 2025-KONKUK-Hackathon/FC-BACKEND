package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.entity.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {
}
