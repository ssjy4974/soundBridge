package com.soundbridge.domain.pronunciation.repository;

import static com.soundbridge.domain.pronunciation.entity.QTryHistory.tryHistory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.pronunciation.entity.TryHistory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TryHistoryRepositoryImpl implements TryHistoryRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<TryHistory> findByBasicLetterIdAndMemberId(Long basicLetterId, Long memberId) {

        return Optional.ofNullable(
            jpaQueryFactory.select(tryHistory)
                .from(tryHistory)
                .where(tryHistory.basicLetter.id.eq(basicLetterId),
                    tryHistory.member.id.eq(memberId))
                .fetchOne());
    }

    @Override
    public Optional<TryHistory> findByDailyWordIdAndMemberId(Long dailyWordId, Long memberId) {
        return Optional.ofNullable(
            jpaQueryFactory.select(tryHistory)
                .from(tryHistory)
                .where(tryHistory.dailyWord.id.eq(dailyWordId),
                    tryHistory.member.id.eq(memberId))
                .fetchOne());
    }


}
