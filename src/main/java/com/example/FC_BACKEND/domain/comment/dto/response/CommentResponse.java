package com.example.FC_BACKEND.domain.comment.dto.response;

import com.example.FC_BACKEND.global.dto.CursorProvider;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentResponse(
        @Schema(description = "댓글 ID")
        Long commentId,

        @Schema(description = "작성자 ID")
        Long writerId,

        @Schema(description = "작성자 이름")
        String writerName,

        @Schema(description = "내용", example = "ㅇㅇㅇ")
        String content,

        @Schema(description = "작성 시간")
        LocalDateTime createdAt
) implements CursorProvider<Long>
{

    @Override
    public Long getCursor() {
        return commentId;
    }
}
