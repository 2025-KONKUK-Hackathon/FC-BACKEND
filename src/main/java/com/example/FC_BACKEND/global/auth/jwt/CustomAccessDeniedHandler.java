package com.example.FC_BACKEND.global.auth.jwt;

import com.example.FC_BACKEND.global.exception.constant.ErrorCode;
import com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode;
import com.example.FC_BACKEND.global.response.BaseErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode error = GlobalErrorCode.FORBIDDEN;
        response.setStatus(error.getHttpStatus());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());

        String json = new ObjectMapper().writeValueAsString(BaseErrorResponse.of(error));
        response.getWriter().write(json);
    }
}
