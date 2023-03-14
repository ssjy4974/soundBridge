package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import java.util.List;

public interface DailyWordRepositorySupport {

    List<DailyWordRes> findAllByMemberId(Long memberId);
}
