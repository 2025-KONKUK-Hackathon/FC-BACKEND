package com.example.FC_BACKEND.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PostCreateRequest(

        @Schema(description = "제목", example = "아니")
        String title,

        @Schema(description = "내용", example = "서버 한명 더 구할걸 그냥 아")
        String content,

        @Schema(description = "이미지")
        List<String> imageUrls,

        @Schema(description = "파트", example = "SERVER")
        String part,

        @Schema(description = "학년", example = "FOURTH")
        String grade,

        @Schema(description = "주제", example = "CLASS")
        String topic,

        @Schema(description = "소속", example = "COMPUTER")
        String affiliation
) {
}
