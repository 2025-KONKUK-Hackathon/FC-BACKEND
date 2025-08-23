package com.example.FC_BACKEND.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmailRequest(
        @Schema(description = "이메일", example = "dlwjddus1112@naver.com")
        String email
) {
}
