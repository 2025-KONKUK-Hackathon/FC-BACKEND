package com.example.FC_BACKEND.global.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum PostErrorCode implements ErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 게시물입니다."),
    POST_UNAUTHORIZED(HttpStatus.FORBIDDEN.value(), "본인의 게시글만 삭제할 수 있습니다."),
    COMMENT_UNAUTHORIZED(HttpStatus.FORBIDDEN.value(), "본인의 댓글만 삭제할 수 있습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 댓글입니다."),
    POST_ALREADY_SCRAP(HttpStatus.CONFLICT.value(), "이미 스크랩한 게시물입니다.")
    ;



    private final int httpStatus;
    private final String message;

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
