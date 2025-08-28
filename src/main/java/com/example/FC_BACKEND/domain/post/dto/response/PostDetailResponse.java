package com.example.FC_BACKEND.domain.post.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostDetailResponse(
        @Schema(description = "작성자 이름", example = "이정연")
        String writerName,

        @Schema(description = "작성자 ID")
        Long writerId,

        @Schema(description = "제목", example = "ㅇㅇ")
        String title,

        @Schema(description = "내용", example = "ㅇㅇㅇ")
        String content,

        @Schema(description = "작성 시간")
        LocalDateTime createdAt,

        @Schema(description = "댓글 수", example = "1")
        int commentCount,

        @Schema(description = "이미지 URL")
        List<String> imageUrls,

        @Schema(description = "학년", example = "FOURTH")
        String grade,

        @Schema(description = "소속", example = "COMPUTER")
        String affiliation,

        @Schema(description = "파트", example = "SERVER")
        String part,

        @Schema(description = "주제", example = "CLASS")
        String topic,

        @Schema(description = "공지 여부")
        boolean isAnnouncement

) {
}
