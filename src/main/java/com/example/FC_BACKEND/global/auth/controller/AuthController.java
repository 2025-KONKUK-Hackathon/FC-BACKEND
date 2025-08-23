package com.example.FC_BACKEND.global.auth.controller;

import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.auth.dto.request.LoginRequest;
import com.example.FC_BACKEND.global.auth.dto.response.LoginResponse;
import com.example.FC_BACKEND.global.auth.service.AuthService;
import com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @Tag(name = "로그인")
    @Operation(summary = "로그인 API")
    @CustomExceptionDescription(SwaggerResponseDescription.LOGIN)
    @PostMapping("login")
    public BaseResponse<LoginResponse> login(@RequestBody @Valid LoginRequest req) {
        return BaseResponse.ok(authService.login(req.email(), req.password()), "로그인에 성공하였습니다.");
    }


}
