package com.example.FC_BACKEND.global.email.service;

import com.example.FC_BACKEND.global.exception.constant.UserErrorCode;
import com.example.FC_BACKEND.global.exception.customexception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    public void sendEmail(String toEmail, String title, String text) {
        SimpleMailMessage emailForm = createEmailForm(toEmail, title, text);

        log.info("from :" + username + "password:" + password);
        try {
            log.info("Sending email to {}", toEmail);
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.error("Failed to send email: {}", e.getMessage(), e);
            throw new CustomException(UserErrorCode.EMAIL_SEND_FAILED);
        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}
