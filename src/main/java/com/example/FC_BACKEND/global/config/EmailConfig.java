package com.example.FC_BACKEND.global.config;

import com.example.FC_BACKEND.global.config.properties.EmailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {

    private final EmailProperties emailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailProperties.host());
        mailSender.setPort(emailProperties.port());
        mailSender.setUsername(emailProperties.username());
        mailSender.setPassword(emailProperties.password());
        mailSender.setDefaultEncoding("UTF-8");

        Properties props = new Properties();
        props.putAll(emailProperties.properties());
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }

}
