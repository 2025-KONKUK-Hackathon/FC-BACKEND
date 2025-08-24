package com.example.FC_BACKEND.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CommentCreateRequest(
        @Schema(description = "게시글 ID")
        Long postId,

        @Schema(description = "댓글 내용", example = "ㄹㅇㅋㅋ")
        String content
) {
}
