package com.example.FC_BACKEND.domain.meeting.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MeetingDetailResponse(
        @Schema(description = "모임 이름", example = "개파 가실 분")
        String meetingName,

        @Schema(description = "모임장 이름", example = "이정연")
        String hostName,

        @Schema(description = "모임장 ID")
        Long hostId,

        @Schema(description = "모집 상태", example = "IN_PROGRESS")
        String meetingStatus,

        @Schema(description = "모집 인원", example = "5")
        int recruitNumber,

        @Schema(description = "신청 인원", example = "2")
        int currentRecruitCount,

        @Schema(description = "모집 시작 기간")
        LocalDate recruitStartDate,

        @Schema(description = "모집 종료 기간")
        LocalDate recruitEndDate,

        @Schema(description = "활동 시작 기간")
        LocalDate actualStartDate,

        @Schema(description = "활동 종료 기간")
        LocalDate actualEndDate,

        @Schema(description = "모임 설명", example = "제발 같이 갈 분")
        String content,

        @Schema(description = "이미지 URL")
        List<String> imageUrls,

        @Schema(description = "모임 카테고리", example = "FRIENDSHIP")
        String category
) {
}
