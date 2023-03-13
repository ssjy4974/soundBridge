package com.soundbridge.domain.pronunciation.repository;

import static com.soundbridge.domain.pronunciation.entity.QDailyWord.dailyWord;
import static com.soundbridge.domain.pronunciation.entity.QTryHistory.tryHistory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import com.soundbridge.domain.pronunciation.response.QDailyWordRes;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DailyWordRepositoryImpl implements DailyWordRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DailyWordRes> findAllByMemberId(Long memberId) {
        return jpaQueryFactory.select(
                new QDailyWordRes(
                    tryHistory.id.as("tryHistoryId"),
                    dailyWord.id.as("dailyWordId"),
                    dailyWord.word.as("word"),
                    dailyWord.guideWord.as("guidWord"),
                    tryHistory.tryCount.as("tryCount"),
                    tryHistory.successCount.as("successCount")
                )
            )
            .from(dailyWord)
            .join(tryHistory).on(tryHistory.dailyWord.id.eq(dailyWord.id))
            .where(tryHistory.member.id.eq(memberId))
            .fetch();
    }
}
