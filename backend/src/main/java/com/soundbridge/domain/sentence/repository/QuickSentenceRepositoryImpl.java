package com.soundbridge.domain.sentence.repository;

import static com.soundbridge.domain.sentence.entity.QQuickSentence.quickSentence;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.sentence.response.QQuickSentenceRes;
import com.soundbridge.domain.sentence.entity.QuickSentence;
import com.soundbridge.domain.sentence.response.QuickSentenceRes;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuickSentenceRepositoryImpl implements QuickSentenceRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QuickSentenceRes> findBySentencesByCategoryId(Long categoryId) {
        return jpaQueryFactory.select(
                new QQuickSentenceRes(
                    quickSentence.id.as("quickSentenceId"),
                    quickSentence.sentence.as("sentence")
                )
            )
            .from(quickSentence)
            .innerJoin(quickSentence.category)
            .where(quickSentence.category.id.eq(categoryId))
            .orderBy(quickSentence.sentenceCount.desc())
            .limit(10)
            .fetch();
    }

}
