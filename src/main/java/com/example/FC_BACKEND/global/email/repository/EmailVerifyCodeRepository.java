package com.example.FC_BACKEND.global.email.repository;

import com.example.FC_BACKEND.global.email.entity.EmailVerifyCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerifyCodeRepository extends JpaRepository<EmailVerifyCode, Long> {
    Optional<EmailVerifyCode> findByEmail(String email);
}
