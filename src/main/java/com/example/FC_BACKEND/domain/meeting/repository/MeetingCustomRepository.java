package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.dto.response.MeetingSummaryResponse;
import com.example.FC_BACKEND.domain.user.entity.User;
import org.springframework.data.domain.Slice;

public interface MeetingCustomRepository {

    public Slice<MeetingSummaryResponse> findAllByCursorId(Long cursorId, int size);

    public User findHostById(Long meetingId);

    public int getCurrentRecruitCount(Long meetingId);
}
