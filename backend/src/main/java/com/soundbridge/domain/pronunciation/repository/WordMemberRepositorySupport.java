package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.entity.PronunciationType;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import java.util.List;

public interface WordMemberRepositorySupport {

    List<DailyWordRes> findAllByMemberId(Long memberId, PronunciationType pronunciationType);

}
