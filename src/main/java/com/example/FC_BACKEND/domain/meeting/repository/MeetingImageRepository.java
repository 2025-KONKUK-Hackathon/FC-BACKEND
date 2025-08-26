package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.entity.Meeting;
import com.example.FC_BACKEND.domain.meeting.entity.MeetingImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingImageRepository extends JpaRepository<MeetingImage, Long> {

    @Query("select mi.url from MeetingImage mi where mi.meeting.id = :meetingId")
    List<String> findAllByMeetingId(Long meetingId);
}
