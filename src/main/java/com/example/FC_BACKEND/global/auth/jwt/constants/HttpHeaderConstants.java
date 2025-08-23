package com.example.FC_BACKEND.global.auth.jwt.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpHeaderConstants {
    public static final String AUTHORIZATION = "authorization";
    public static final String BEARER_PREFIX = "Bearer ";
}
