package com.example.FC_BACKEND.domain.post.repository;

import com.example.FC_BACKEND.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
