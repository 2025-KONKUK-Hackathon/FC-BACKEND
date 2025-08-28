package com.example.FC_BACKEND.global.auth.jwt;

import com.example.FC_BACKEND.global.auth.jwt.constants.HttpHeaderConstants;
import com.example.FC_BACKEND.global.auth.jwt.constants.SwaggerPathConstants;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import com.example.FC_BACKEND.global.response.BaseErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private static final List<String> EXCLUDED_PATH_PREFIXES = List.of(
            SwaggerPathConstants.SWAGGER_CONFIG,
            SwaggerPathConstants.SWAGGER_UI,
            SwaggerPathConstants.SWAGGER_DOCS
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if (token != null) {
            try {
                if (jwtUtil.isTokenValid(token)) {
                    Authentication authentication = getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (CustomException e) {

                var errorCode = e.getErrorCode();
                BaseErrorResponse body = BaseErrorResponse.of(errorCode);

                response.setStatus(errorCode.getHttpStatus());
                response.setContentType("application/json;charset=UTF-8");

                com.fasterxml.jackson.databind.ObjectMapper om = new com.fasterxml.jackson.databind.ObjectMapper();
                response.getWriter().write(om.writeValueAsString(body));
                return;
            }
        }


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String uri = request.getRequestURI();
        return EXCLUDED_PATH_PREFIXES.stream().anyMatch(uri::startsWith);
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaderConstants.AUTHORIZATION);
        String validTokenPrefix = HttpHeaderConstants.BEARER_PREFIX;
        if (authorization == null || !authorization.startsWith(validTokenPrefix)) {
            return null;
        }
        return authorization.substring(validTokenPrefix.length()).trim();
    }

    private Authentication getAuthentication(String token) {
        Long userId = jwtUtil.extractUserIdFromToken(token);
        return new JwtTokenAuthentication(userId);
    }

}

