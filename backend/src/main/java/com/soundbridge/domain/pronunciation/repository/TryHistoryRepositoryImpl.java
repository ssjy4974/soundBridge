package com.soundbridge.domain.pronunciation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TryHistoryRepositoryImpl implements TryHistoryRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

}
