package com.example.FC_BACKEND.domain.post.dto.response;

import com.example.FC_BACKEND.global.dto.CursorProvider;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record PostSummaryResponse(
        @Schema(description = "게시글 ID")
        Long postId,

        @Schema(description = "작성자 ID")
        Long writerId,

        @Schema(description = "작성자 이름")
        String writerName,

        @Schema(description = "제목", example = "아니")
        String title,

        @Schema(description = "내용", example = "진짜 서버 한 명만 더..")
        String content,

        @Schema(description = "댓글 수")
        int commentCount,

        @Schema(description = "썸네일 이미지 url")
        String imageUrl,

        @Schema(description = "작성 시간")
        LocalDateTime createdAt,

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
) implements CursorProvider<Long>
{

    @Override
    public Long getCursor() {
        return postId;
    }
}
