package com.example.FC_BACKEND.domain.post.repository;

import com.example.FC_BACKEND.domain.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
