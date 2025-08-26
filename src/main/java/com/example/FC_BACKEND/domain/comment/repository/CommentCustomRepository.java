package com.example.FC_BACKEND.domain.comment.repository;

import com.example.FC_BACKEND.domain.comment.dto.response.CommentResponse;
import org.springframework.data.domain.Slice;

public interface CommentCustomRepository {
    Slice<CommentResponse> findByPostId(Long postId, Long cursorId, int size);
}
