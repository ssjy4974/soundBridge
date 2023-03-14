package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.entity.TryHistory;
import java.util.Optional;

public interface TryHistoryRepositorySupport {

    Optional<TryHistory> findByBasicLetterIdAndMemberId(Long basicLetterId, Long memberId);

    Optional<TryHistory> findByDailyWordIdAndMemberId(Long dailyWordId, Long memberId);
}
