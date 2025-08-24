package com.example.FC_BACKEND.domain.post.repository;

import com.example.FC_BACKEND.domain.post.dto.response.PostSummaryResponse;
import org.springframework.data.domain.Slice;

public interface PostCustomRepository {

    public Slice<PostSummaryResponse> findAllByCursorId(Long cursorId, int size);

}
