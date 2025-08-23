package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
