package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.meeting.dto.response.MeetingSummaryResponse;
import org.springframework.data.domain.Slice;

public interface MeetingCustomRepository {

    public Slice<MeetingSummaryResponse> findAllByCursorId(Long cursorId, int size);
}
