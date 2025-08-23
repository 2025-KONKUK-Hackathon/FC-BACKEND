package com.example.FC_BACKEND.domain.comment.repository;

import com.example.FC_BACKEND.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
