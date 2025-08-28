package com.example.FC_BACKEND.domain.post.repository;

import com.example.FC_BACKEND.domain.post.entity.PostScrap;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostScrapRepository extends JpaRepository<PostScrap, Long> {

    Optional<PostScrap> findByUserIdAndPostId(Long userId, Long postId);



}
