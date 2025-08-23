package com.example.FC_BACKEND;

import com.example.FC_BACKEND.global.config.properties.EmailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(EmailProperties.class)
public class FcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcBackendApplication.class, args);
	}

}
