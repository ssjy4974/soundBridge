package com.soundbridge.domain.pronunciation.repository;

import static com.soundbridge.domain.pronunciation.entity.QDailyWord.dailyWord;
import static com.soundbridge.domain.pronunciation.entity.QTryHistory.tryHistory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.pronunciation.entity.PronunciationType;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import com.soundbridge.domain.pronunciation.response.QDailyWordRes;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DailyWordRepositoryImpl implements DailyWordRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public DailyWordRes findDailyWordBydailyWord(String word) {
        return jpaQueryFactory.select(
                new QDailyWordRes(
                    dailyWord.id.as("dailyWordId"),
                    dailyWord.word.as("word"),
                    dailyWord.guideWord.as("guideWord")
                )
            )
            .from(dailyWord)
            .fetchOne();
    }
}
