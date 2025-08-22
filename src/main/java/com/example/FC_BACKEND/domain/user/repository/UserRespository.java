package com.example.FC_BACKEND.domain.user.repository;

import com.example.FC_BACKEND.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
