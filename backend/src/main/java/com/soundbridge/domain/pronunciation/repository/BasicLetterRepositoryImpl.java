package com.soundbridge.domain.pronunciation.repository;

import static com.soundbridge.domain.pronunciation.entity.QBasicLetter.basicLetter;
import static com.soundbridge.domain.pronunciation.entity.QTryHistory.tryHistory;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.pronunciation.entity.PronunciationType;
import com.soundbridge.domain.pronunciation.response.BasicLetterRes;
import com.soundbridge.domain.pronunciation.response.QBasicLetterRes;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicLetterRepositoryImpl implements BasicLetterRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BasicLetterRes> findAllByMemberId(Long memberId) {
        return jpaQueryFactory.select(
                new QBasicLetterRes(
                    tryHistory.id.as("tryHistoryId"),
                    basicLetter.id.as("basicLetterId"),
                    basicLetter.letter.as("letter"),
                    basicLetter.guideLetter.as("guidLetter"),
                    basicLetter.letterImage.as("letterImage"),
                    basicLetter.guideImage.as("guidImage"),
                    tryHistory.tryCount.as("tryCount"),
                    tryHistory.successCount.as("successCount")
                )
            )
            .from(basicLetter)
            .leftJoin(tryHistory)
            .on(tryHistory.type.eq(PronunciationType.BASIC_LETTER),
                tryHistory.basicLetter.id.eq(basicLetter.id),
                tryHistory.member.id.eq(memberId))
            .fetch();
    }
}
