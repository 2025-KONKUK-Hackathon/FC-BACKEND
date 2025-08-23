package com.example.FC_BACKEND.global.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "spring.mail")
public record EmailProperties(
        String host,
        int port,
        String username,
        String password,
        Map<String, String> properties,
        Long authCodeExpirationMillis
) {
}
