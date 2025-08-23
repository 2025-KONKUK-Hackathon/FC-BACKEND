package com.example.FC_BACKEND.global.auth.jwt;

import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProvider jwtProvider;

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProvider.getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException | io.jsonwebtoken.security.SignatureException e) {
            throw new CustomException(JWT_INVALID_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw new CustomException(JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw new CustomException(JWT_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(JWT_INVALID);
        } catch (Exception e) {
            log.warn("Unexpected JWT error: {}", e.getMessage());
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }
    }

    public Long extractUserIdFromToken(String token) {
        try {
            Claims claims = getClaims(token);
            return Long.valueOf(claims.getSubject());
        } catch (Exception e) {
            throw new CustomException(JWT_USER_ID_EXTRACTION_FAILED);
        }
    }

}
