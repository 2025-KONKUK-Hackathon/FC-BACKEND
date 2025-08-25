package com.example.FC_BACKEND.domain.post.repository;

import com.example.FC_BACKEND.domain.comment.entity.QComment;
import com.example.FC_BACKEND.domain.post.dto.response.PostSummaryResponse;
import com.example.FC_BACKEND.domain.post.entity.QPost;
import com.example.FC_BACKEND.domain.post.entity.QPostImage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;



    @Override
    public Slice<PostSummaryResponse> findAllByCursorId(Long cursorId, int size) {
        QPost post = QPost.post;
        QComment comment = QComment.comment;
        QPostImage postImage = QPostImage.postImage;
        QPostImage minPostImage = new QPostImage("minPostImage");

        var thumbnailUrlSubquery =
                JPAExpressions.select(postImage.url)
                        .from(postImage)
                        .where(
                                postImage.post.eq(post),
                                postImage.id.eq(
                                        JPAExpressions.select(minPostImage.id.min())
                                                .from(minPostImage)
                                                .where(minPostImage.post.eq(post))
                                )
                        );

        List<PostSummaryResponse> content = queryFactory
                .select(Projections.constructor(PostSummaryResponse.class,
                        post.id,
                        post.user.id,
                        post.user.name,
                        post.title,
                        post.content,
                        comment.id.count().intValue(),
                        thumbnailUrlSubquery,
                        post.createdAt
                ))
                .from(post)
                .leftJoin(comment).on(comment.post.eq(post))
                .where(
                        cursorId != null ? post.id.lt(cursorId) : null
                )
                .groupBy(post.id)
                .orderBy(post.id.desc())
                .limit(size + 1)
                .fetch();

        boolean hasNext = content.size() > size;
        if (hasNext) content.remove(size);

        return new SliceImpl<>(content, PageRequest.of(0, size), hasNext);

    }
}
