package com.example.FC_BACKEND.global.config.swagger;

import com.example.FC_BACKEND.global.exception.constant.ErrorCode;
import com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode;
import com.example.FC_BACKEND.global.exception.constant.ImageErrorCode;
import com.example.FC_BACKEND.global.exception.constant.UserErrorCode;
import lombok.Getter;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.FC_BACKEND.global.exception.constant.ImageErrorCode.*;
import static com.example.FC_BACKEND.global.exception.constant.UserErrorCode.*;

@Getter
public enum SwaggerResponseDescription {

    COMMON(new LinkedHashSet<>(Set.of(
    ))),

    SEND_EMAIL_CODE(new LinkedHashSet<>(Set.of(
            EMAIL_SEND_FAILED,
            INVALID_EMAIL_TYPE,
            INVALID_EMAIL,
            EMAIL_DUPLICATE
    ))),
    VERIFY_CODE(new LinkedHashSet<>(Set.of(
            VERIFY_CODE_MISMATCH,
            VERIFY_CODE_NOT_FOUND
    ))),
    SIGNUP(new LinkedHashSet<>(Set.of(
            NOT_VERIFIED_EMAIL,
            EMAIL_DUPLICATE
    ))),
    LOGIN(new LinkedHashSet<>(Set.of(
            USER_NOT_FOUND,
            PASSWORD_NOT_MATCH
    ))),
    UPLOAD_IMAGE(new LinkedHashSet<>(Set.of(
            NOT_IMAGE,
            UNSUPPORTED_MEDIA_TYPE,
            UNSUPPORTED_IMAGE_TYPE
    )))
    ;
    private final Set<ErrorCode> errorCodeList;
    SwaggerResponseDescription(Set<ErrorCode> specificErrorCodes) {
        this.errorCodeList = new LinkedHashSet<>();
        this.errorCodeList.addAll(specificErrorCodes);
        this.errorCodeList.addAll(getGlobalErrorCodes());
    }

    private Set<ErrorCode> getGlobalErrorCodes() {
        return new LinkedHashSet<>(Set.of(GlobalErrorCode.values()));
    }
}
