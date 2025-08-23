package com.example.FC_BACKEND.global.auth.jwt;

import com.example.FC_BACKEND.global.exception.constant.ErrorCode;
import com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode;
import com.example.FC_BACKEND.global.response.BaseErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ErrorCode error = GlobalErrorCode.UNAUTHORIZED;

        var requestError = request.getAttribute("exception");
        if(requestError instanceof ErrorCode errorCode){
            error = errorCode;
        }
        response.setStatus(error.getHttpStatus());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8.name());

        String json = new ObjectMapper().writeValueAsString(BaseErrorResponse.of(error));
        response.getWriter().write(json);
    }

}
