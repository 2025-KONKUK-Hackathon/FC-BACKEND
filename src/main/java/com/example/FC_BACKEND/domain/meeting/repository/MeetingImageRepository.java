package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.entity.MeetingImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingImageRepository extends JpaRepository<MeetingImage, Long> {
}
