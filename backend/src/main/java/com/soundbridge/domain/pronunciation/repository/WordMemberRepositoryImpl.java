package com.soundbridge.domain.pronunciation.repository;

import static com.soundbridge.domain.pronunciation.entity.QDailyWord.dailyWord;
import static com.soundbridge.domain.pronunciation.entity.QWordMember.wordMember;
import static com.soundbridge.domain.pronunciation.entity.QTryHistory.tryHistory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.pronunciation.entity.PronunciationType;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import com.soundbridge.domain.pronunciation.response.QDailyWordRes;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WordMemberRepositoryImpl implements WordMemberRepositorySupport{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<DailyWordRes> findAllByMemberId(Long memberId, PronunciationType pronunciationType) {
        return jpaQueryFactory.select(
                new QDailyWordRes(
                    wordMember.id.as("wordMemberId"),
                    dailyWord.word.as("word"),
                    dailyWord.guideWord.as("guidWord"),
                    tryHistory.tryCount.as("tryCount"),
                    tryHistory.successCount.as("successCount")
                )
            )
            .from(wordMember)
            .leftJoin(tryHistory)
            .on(wordMember.id.eq(tryHistory.wordMember.id))
            .innerJoin(dailyWord)
            .on(wordMember.dailyWord.id.eq(dailyWord.id), wordMember.dailyWord.type.eq(pronunciationType))
            .where(wordMember.member.id.eq(memberId))
            .orderBy(wordMember.id.desc())
            .fetch();
    }
}
