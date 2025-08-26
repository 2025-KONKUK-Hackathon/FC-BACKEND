package com.example.FC_BACKEND.domain.meeting.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record MeetingRecruitStatusResponse(
        @Schema(description = "모임 멤버 ID")
        Long meetingMemberId,

        @Schema(description = "모임 멤버 이름")
        String name
) {
}
