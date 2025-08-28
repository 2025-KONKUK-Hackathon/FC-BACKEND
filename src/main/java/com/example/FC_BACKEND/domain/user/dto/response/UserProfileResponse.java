package com.example.FC_BACKEND.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserProfileResponse(
        @Schema(description = "이름", example = "이정연")
        String name,

        @Schema(description = "전화번호", example = "01021246390")
        String phone,

        @Schema(description = "학번", example = "202011346")
        String studentNumber
) {
    public static UserProfileResponse of(String name, String phone, String studentNumber) {
        return new UserProfileResponse(name, phone, studentNumber);
    }
}
