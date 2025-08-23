package com.example.FC_BACKEND.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserSignUpRequest(
        @Schema(description = "이메일", example = "dlwjddus1112@naver.com")
        String email,

        @Schema(description = "이름", example = "이정연")
        String name,

        @Schema(description = "비밀번호", example = "1234")
        String password,

        @Schema(description = "학번", example = "202011346")
        String studentNumber,

        @Schema(description = "전화번호",example = "01021246390")
        String phone

) {
}
