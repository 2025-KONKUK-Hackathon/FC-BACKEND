package com.example.FC_BACKEND.domain.post.repository;

import com.example.FC_BACKEND.domain.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    List<PostImage> findAllByPostId(Long postId);

    @Query("select pi.url from PostImage pi where pi.post.id = :postId")
    List<String> findAllUrlByPostId(Long postId);
}
