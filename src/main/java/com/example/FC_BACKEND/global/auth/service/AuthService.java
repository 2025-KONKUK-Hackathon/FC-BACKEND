package com.example.FC_BACKEND.global.auth.service;

import com.example.FC_BACKEND.domain.user.entity.User;
import com.example.FC_BACKEND.domain.user.repository.UserRespository;
import com.example.FC_BACKEND.global.auth.dto.request.LoginRequest;
import com.example.FC_BACKEND.global.auth.dto.response.LoginResponse;
import com.example.FC_BACKEND.global.auth.jwt.JwtProvider;
import com.example.FC_BACKEND.global.exception.constant.UserErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.FC_BACKEND.global.exception.constant.UserErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;

    private final UserRespository userRespository;

    @Transactional
    public LoginResponse login(String email, String password) {
        User user = userRespository.findByEmail(email).orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if(!user.getPassword().equals(password)) throw new CustomException(PASSWORD_NOT_MATCH);

        String accessToken = jwtProvider.generateAccessToken(user.getId());

        return LoginResponse.of(accessToken, user.getId());

    }
}
