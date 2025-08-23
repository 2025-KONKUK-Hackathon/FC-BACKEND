package com.example.FC_BACKEND.global.email.entity;

import com.example.FC_BACKEND.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class EmailVerifyCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_verify_code_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String verifyCode;

    @Builder.Default
    private boolean isVerified = false;

    public static EmailVerifyCode create(String email, String verifyCode) {
        return EmailVerifyCode.builder()
                .email(email)
                .verifyCode(verifyCode)
                .build();
    }
}
