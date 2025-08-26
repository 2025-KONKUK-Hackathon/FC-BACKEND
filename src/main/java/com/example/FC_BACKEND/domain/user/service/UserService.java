package com.example.FC_BACKEND.domain.user.service;

import com.example.FC_BACKEND.domain.user.entity.User;
import com.example.FC_BACKEND.domain.user.repository.UserRespository;
import com.example.FC_BACKEND.global.email.entity.EmailVerifyCode;
import com.example.FC_BACKEND.global.email.repository.EmailVerifyCodeRepository;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import com.example.FC_BACKEND.global.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import static com.example.FC_BACKEND.global.exception.constant.GlobalErrorCode.*;
import static com.example.FC_BACKEND.global.exception.constant.UserErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRespository userRespository;

    private final EmailVerifyCodeRepository emailVerifyCodeRepository;

    private static final String VALID_EMAIL_SUFFIX = "konkuk.ac.kr";

    private final EmailService emailService;

    @Transactional
    public void sendCodeToEmail(String toEmail) {
        validateEmail(toEmail);
        String title = "[Feat/Connect] 이메일 인증 번호";
        String authCode = this.createCode();

        emailService.sendEmail(toEmail, title, authCode);
        Optional<EmailVerifyCode> verifyCode = emailVerifyCodeRepository.findByEmail(toEmail);
        if(verifyCode.isPresent()){
            EmailVerifyCode code = verifyCode.get();
            code.setVerifyCode(authCode);
            return;
        }
        emailVerifyCodeRepository.save(EmailVerifyCode.create(toEmail, authCode));
    }

    @Transactional
    public void verifyCode(String email, String code){
        EmailVerifyCode verifyCode = emailVerifyCodeRepository.findByEmail(email).orElseThrow(() -> new CustomException(VERIFY_CODE_NOT_FOUND));
        if(!Objects.equals(verifyCode.getVerifyCode(), code)) throw new CustomException(VERIFY_CODE_MISMATCH);
        verifyCode.setVerified(true);
        emailVerifyCodeRepository.save(verifyCode);
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        if(!email.matches(emailRegex)) throw new CustomException(INVALID_EMAIL_TYPE);

        if(!email.endsWith(VALID_EMAIL_SUFFIX)) throw new CustomException(INVALID_EMAIL);

        if(userRespository.existsByEmail(email)) throw new CustomException(EMAIL_DUPLICATE);

    }

    @Transactional
    public void signup(String email, String name, String password, String studentNumber, String phone){
        if(userRespository.existsByEmail(email)) throw new CustomException(EMAIL_DUPLICATE);
        EmailVerifyCode verifyCode = emailVerifyCodeRepository.findByEmail(email).orElseThrow(() -> new CustomException(VERIFY_CODE_NOT_FOUND));
        if(!verifyCode.isVerified()) throw new CustomException(NOT_VERIFIED_EMAIL);
        userRespository.save(User.create(email, name, password, phone, studentNumber));
    }

    public User findUser(Long userId){
        return userRespository.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

}
