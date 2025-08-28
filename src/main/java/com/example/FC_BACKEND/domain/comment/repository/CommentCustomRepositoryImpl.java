package com.example.FC_BACKEND.domain.comment.repository;

import com.example.FC_BACKEND.domain.comment.dto.response.CommentResponse;
import com.example.FC_BACKEND.domain.comment.entity.QComment;
import com.example.FC_BACKEND.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {

    private final JPAQueryFactory queryFactory;
    private final QComment comment = QComment.comment;
    private final QUser user = QUser.user;


    @Override
    public Slice<CommentResponse> findByPostId(Long postId, Long cursorId, int size) {
        List<CommentResponse> content = queryFactory
                .select(Projections.constructor(CommentResponse.class,
                        comment.id,
                        comment.user.id,
                        user.name,
                        comment.content,
                        comment.createdAt
                ))
                .from(comment)
                .leftJoin(user).on(user.eq(comment.user))
                .where(
                        comment.post.id.eq(postId),
                        cursorId != null ? comment.id.lt(cursorId) : null
                )
                .groupBy(comment.id)
                .orderBy(comment.id.desc())
                .limit(size + 1)
                .fetch();

        boolean hasNext = content.size() > size;
        if (hasNext) content.remove(size);

        return new SliceImpl<>(content, PageRequest.of(0, size), hasNext);
    }

}
