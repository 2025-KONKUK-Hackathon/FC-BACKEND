package com.example.FC_BACKEND.global.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "이메일을 입력해주세요.")
        @Schema(description = "이메일", example = "dlwjddus1112@naver.com")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Schema(description = "비밀번호", example = "1234")
        String password
) {
}
