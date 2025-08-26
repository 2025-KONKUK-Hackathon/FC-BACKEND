package com.example.FC_BACKEND.domain.meeting.dto.response;

import com.example.FC_BACKEND.global.dto.CursorProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public record MeetingSummaryResponse(
        @Schema(description = "모임 ID")
        Long meetingId,

        @Schema(description = "모임장 이름", example = "이정연")
        String hostName,

        @Schema(description = "모임 이름", example = "개파갈사람")
        String meetingName,

        @Schema(description = "모집 인원", example = "5")
        int recruitNumber,

        @Schema(description = "신청 인원", example = "1")
        int currentRecruitNumber,

        @Schema(description = "모임 카테고리", example = "FREINDSHIP")
        String category,

        @Schema(description = "썸네일 이미지 url")
        String imageUrl
) implements CursorProvider<Long> {
    @Override
    public Long getCursor() {
        return meetingId;
    }

    public static MeetingSummaryResponse of(Long meetingId, String hostName, String meetingName, int recruitNumber,
                                            int currentRecruitNumber, String category, String imageUrl) {
        return new MeetingSummaryResponse(meetingId, hostName,  meetingName, recruitNumber, currentRecruitNumber, category, imageUrl);
    }
}
