package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.response.BasicLetterRes;
import java.util.List;

public interface BasicLetterRepositorySupport {

    List<BasicLetterRes> findAllByMemberId(Long memberId);
}
