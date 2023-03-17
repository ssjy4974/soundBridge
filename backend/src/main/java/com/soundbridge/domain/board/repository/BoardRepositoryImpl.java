package com.soundbridge.domain.board.repository;

import static com.soundbridge.domain.board.entity.QFeedbackBoard.feedbackBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.board.response.BoardDetailRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<BoardDetailRes> findAllWithPaging(Pageable pageable, Long cursorId, Long memberId) {

        final List<BoardDetailRes> fetch = jpaQueryFactory.select(
                Projections.fields(BoardDetailRes.class,
                    feedbackBoard.id.as("feedbackBoardId"),
                    feedbackBoard.title.as("title"),
                    feedbackBoard.member.id.as("writerId"),
                    feedbackBoard.member.nickname.as("nickname"),
                    feedbackBoard.startTime.as("startTime"),
                    feedbackBoard.endTime.as("endTime")))
            .from(feedbackBoard)
            .where(memberIdEq(memberId), cursorId(cursorId))
            .limit(pageable.getPageSize() + 1)
            .orderBy(feedbackBoard.id.desc())
            .fetch();

        boolean hasNext = false;

        if(fetch.size() == pageable.getPageSize() + 1) {
            fetch.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(fetch, pageable, hasNext);
    }

    private BooleanBuilder memberIdEq(Long memberId) {
        if(memberId == null) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(feedbackBoard.member.id.eq(memberId));
    }

    private BooleanExpression cursorId(Long cursorId) {
        return cursorId == null ? null : feedbackBoard.id.lt(cursorId);
    }

}
