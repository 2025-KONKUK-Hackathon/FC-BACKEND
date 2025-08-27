package com.example.FC_BACKEND.domain.meeting.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record MeetingMemberResponse(
        @Schema(description = "모임 멤버 ID")
        Long meetingMemberId,

        @Schema(description = "모임 멤버 이름")
        String name,

        @Schema(description = "모임 멤버 전화번호")
        String phone,

        @Schema(description = "모임 멤버 학번")
        String studentNumber,

        @Schema(description = "신청 시각")
        LocalDateTime registerAt,

        @Schema(description = "모임장 여부")
        boolean isHost
) {
}
