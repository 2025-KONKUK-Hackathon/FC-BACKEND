package com.example.FC_BACKEND.domain.meeting.dto.request;

import com.example.FC_BACKEND.global.annotation.ValidCategory;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record MeetingCreateRequest(
        @Schema(description = "모임 이름", example = "개강파티 같이 가실 분 ㅠ")
        String meetingName,

        @Schema(description = "모임 설명", example = "개강파티 가고 싶은데 전과생인데 아는 사람이 없어서 같이 가실 분 구해요 ")
        String content,

        @ValidCategory
        @Schema(description = "모임 카테고리", example = "FRIENDSHIP")
        String category,

        @Schema(description = "모집 인원", example = "4")
        int recruitNumber,

        @Schema(description = "신청 시작 날짜", example = "2025-09-01")
        LocalDate recruitStartDate,

        @Schema(description = "신청 마감 날짜", example = "2025-09-02")
        LocalDate recruitEndDate,

        @Schema(description = "활동 시작 날짜", example = "2025-09-03")
        LocalDate actualStartDate,

        @Schema(description = "활동 마감 날짜", example = "2025-09-04")
        LocalDate actualEndDate,

        @Schema(description = "사진")
        List<String> imageUrls
){
}
