package com.example.FC_BACKEND.domain.user.controller;

import com.example.FC_BACKEND.domain.user.dto.request.EmailRequest;
import com.example.FC_BACKEND.domain.user.dto.request.UserSignUpRequest;
import com.example.FC_BACKEND.domain.user.dto.response.UserProfileResponse;
import com.example.FC_BACKEND.domain.user.service.UserService;
import com.example.FC_BACKEND.global.annotation.CustomExceptionDescription;
import com.example.FC_BACKEND.global.annotation.LoginUserId;
import com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription;
import com.example.FC_BACKEND.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.FC_BACKEND.global.config.swagger.SwaggerResponseDescription.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Tag(name = "회원가입 관련 API")
    @Operation(summary = "이메일 인증번호 전송")
    @CustomExceptionDescription(SEND_EMAIL_CODE)
    @PostMapping("emails/verification-requests")
    public BaseResponse<Void> sendEmail(@RequestBody EmailRequest req){
        userService.sendCodeToEmail(req.email());
        return BaseResponse.ok(null, "인증번호가 전송되었습니다.");
    }

    @Tag(name = "회원가입 관련 API")
    @Operation(summary = "인증번호 확인")
    @CustomExceptionDescription(VERIFY_CODE)
    @PostMapping("emails/verifications")
    public BaseResponse<Void> verify(@RequestParam String email, @RequestParam String code){
        userService.verifyCode(email,code);
        return BaseResponse.ok(null,"인증에 성공하였습니다.");
    }

    @Tag(name = "회원가입 관련 API")
    @Operation(summary = "회원가입")
    @CustomExceptionDescription(SIGNUP)
    @PostMapping("signup")
    public BaseResponse<Void> signup(@RequestBody UserSignUpRequest req){
        userService.signup(req.email(),req.name(),req.password(),req.studentNumber(),req.phone());
        return BaseResponse.create(null,"회원가입에 성공하였습니다.");
    }

    @Tag(name = "마이페이지 관련 API")
    @Operation(summary = "유저 프로필 조회")
    @CustomExceptionDescription(USER_PROFILE)
    @GetMapping("info")
    public BaseResponse<UserProfileResponse> getUserProfile(
            @LoginUserId @Parameter(hidden = true) Long userId
    ){
        return BaseResponse.ok(userService.getUserProfile(userId),"유저 프로필 조회에 성공하였습니다.");
    }

}
