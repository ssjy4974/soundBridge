package com.soundbridge.domain.sentence.repository;

import static com.soundbridge.domain.sentence.entity.QMySentenceLog.mySentenceLog;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.sentence.entity.MySentenceLog;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MySentenceLogRepositoryImpl implements MySentenceLogRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MySentenceLog> findOne(String sentence, Long memberId) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(mySentenceLog)
                .where(mySentenceLog.sentenceLog.eq(sentence),
                    mySentenceLog.member.id.eq(memberId))
                .fetchOne());
    }

}
