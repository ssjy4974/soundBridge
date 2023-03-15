package com.soundbridge.domain.meeting.repository;

import static com.soundbridge.domain.board.entity.QFeedbackBoard.feedbackBoard;
import static com.soundbridge.domain.meeting.entity.QMeeting.meeting;
import static com.soundbridge.domain.member.entity.QMember.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.meeting.response.MeetingDetailRes;
import com.soundbridge.domain.meeting.response.QMeetingDetailRes;
import com.soundbridge.domain.member.entity.Role;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class MeetingRepositoryImpl implements MeetingRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Slice<MeetingDetailRes> findAll(Pageable pageable, Long cursorId, Long memberId, Role role) {
        final List<MeetingDetailRes> fetch = jpaQueryFactory.select(
                new QMeetingDetailRes(
                    meeting.id.as("meetingId"),
                    meeting.title.as("title"),
                    meeting.code.as("code"),
                    meeting.helper.nickname.as("helperName"),
                    meeting.helper.profile.as("helperProfile"),
                    meeting.applicant.nickname.as("applicantName"),
                    meeting.applicant.profile.as("applicantProfile")
                )
            )
            .from(meeting)
            .innerJoin(meeting.helper, member)
            .innerJoin(meeting.applicant, member)
            .where(roleEq(memberId, role), cursorId(cursorId))
            .limit(pageable.getPageSize() + 1)
            .orderBy(meeting.id.desc())
            .fetch();

        boolean hasNext = false;

        if(fetch.size() == pageable.getPageSize() + 1) {
            fetch.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(fetch, pageable, hasNext);
    }

    private BooleanExpression roleEq(Long memberId, Role role) {
        if (role.equals(Role.APPLICANT)) {
            return meeting.applicant.id.eq(memberId);
        } else {
            return meeting.helper.id.eq(memberId);
        }
    }

    private BooleanExpression cursorId(Long cursorId) {
        return cursorId == null ? null : meeting.id.lt(cursorId);
    }
}
