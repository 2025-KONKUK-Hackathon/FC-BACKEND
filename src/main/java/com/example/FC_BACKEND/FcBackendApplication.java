package com.example.FC_BACKEND;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcBackendApplication.class, args);
	}

}
