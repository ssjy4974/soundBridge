package com.soundbridge.domain.sentence.repository;

import static com.soundbridge.domain.sentence.entity.QMySentenceLog.mySentenceLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.sentence.entity.MySentenceLog;
import com.soundbridge.domain.sentence.response.MySentenceLogRes;
import com.soundbridge.domain.sentence.response.QMySentenceLogRes;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MySentenceLogRepositoryImpl implements MySentenceLogRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MySentenceLogRes> findBySentenceAndMemberId(String sentence, Long memberId) {
        return jpaQueryFactory.select(
                new QMySentenceLogRes(
                    mySentenceLog.id.as("mySentenceLogId"),
                    mySentenceLog.sentenceLog.as("sentence")
                )
            )
            .from(mySentenceLog)
            .innerJoin(mySentenceLog.member)
            .where(mySentenceLog.member.id.eq(memberId),
                mySentenceLog.sentenceLog.contains(sentence))
            .orderBy(mySentenceLog.count.desc())
            .limit(5)
            .fetch();
    }

    @Override
    public Optional<MySentenceLog> findOne(String sentence, Long memberId) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(mySentenceLog)
                .where(mySentenceLog.sentenceLog.eq(sentence),
                    mySentenceLog.member.id.eq(memberId))
                .fetchOne());
    }

}
