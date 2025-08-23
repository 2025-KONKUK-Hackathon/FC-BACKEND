package com.example.FC_BACKEND.global.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(description = "Access Token")
        String accessToken,

        @Schema(description = "유저 ID", example = "1")
        Long userId
) {
    public static LoginResponse of(String accessToken, Long userId) {
        return new LoginResponse(accessToken, userId);
    }
}
