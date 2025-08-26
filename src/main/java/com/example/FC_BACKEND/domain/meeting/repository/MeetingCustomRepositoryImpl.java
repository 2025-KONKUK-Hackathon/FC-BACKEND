package com.example.FC_BACKEND.domain.meeting.repository;

import com.example.FC_BACKEND.domain.comment.entity.QComment;
import com.example.FC_BACKEND.domain.meeting.dto.response.MeetingSummaryResponse;
import com.example.FC_BACKEND.domain.meeting.entity.QMeeting;
import com.example.FC_BACKEND.domain.meeting.entity.QMeetingImage;
import com.example.FC_BACKEND.domain.meeting.entity.QMeetingMember;
import com.example.FC_BACKEND.domain.post.dto.response.PostSummaryResponse;
import com.example.FC_BACKEND.domain.post.entity.QPost;
import com.example.FC_BACKEND.domain.post.entity.QPostImage;
import com.example.FC_BACKEND.domain.user.entity.QUser;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingCustomRepositoryImpl implements MeetingCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<MeetingSummaryResponse> findAllByCursorId(Long cursorId, int size) {
        QMeeting meeting = QMeeting.meeting;
        QMeetingMember mm = QMeetingMember.meetingMember;
        QMeetingMember mmHost = new QMeetingMember("mmHost");
        QUser user = QUser.user;
        QMeetingImage mi = QMeetingImage.meetingImage;
        QMeetingImage mi2 = new QMeetingImage("mi2");

        Expression<Integer> currentRecruitCount =
                ExpressionUtils.as(
                        JPAExpressions.select(mm.id.count().intValue())
                                .from(mm)
                                .where(mm.meeting.eq(meeting)),
                        "currentRecruitCount"
                );

        var hostNameSubquery = JPAExpressions
                .select(user.name)
                .from(mmHost)
                .join(mmHost.user, user)
                .where(
                        mmHost.meeting.eq(meeting),
                        mmHost.isHost.isTrue()
                );

        var thumbnailUrlSubquery = JPAExpressions
                .select(mi.url)
                .from(mi)
                .where(
                        mi.meeting.eq(meeting),
                        mi.id.eq(
                                JPAExpressions
                                        .select(mi2.id.min())
                                        .from(mi2)
                                        .where(mi2.meeting.eq(meeting))
                        )
                );

        List<MeetingSummaryResponse> content = queryFactory
                .select(Projections.constructor(MeetingSummaryResponse.class,
                        meeting.id,
                        hostNameSubquery,
                        meeting.name,
                        meeting.recruitNumber,
                        currentRecruitCount,
                        meeting.category.stringValue(),
                        thumbnailUrlSubquery
                ))
                .from(meeting)
                .where(cursorId != null ? meeting.id.lt(cursorId) : null)
                .orderBy(meeting.id.desc())
                .limit(size + 1)
                .fetch();

        boolean hasNext = content.size() > size;
        if (hasNext) content.remove(size);

        return new SliceImpl<>(content, PageRequest.of(0, size), hasNext);
    }
}
