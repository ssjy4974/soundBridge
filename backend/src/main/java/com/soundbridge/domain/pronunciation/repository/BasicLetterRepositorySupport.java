package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.response.BasicLetterRes;
import java.util.List;
import java.util.Optional;

public interface BasicLetterRepositorySupport {

    List<BasicLetterRes> findAllByMemberId(Long memberId);

    Optional<BasicLetterRes> findOne(Long basicLetterId);
}
