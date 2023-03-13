package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.MySentenceLog;
import com.soundbridge.domain.sentence.response.MySentenceLogRes;
import java.util.List;
import java.util.Optional;

public interface MySentenceLogRepositorySupport {

    List<MySentenceLogRes> findBySentenceAndMemberId(String sentence, Long memberId);

    Optional<MySentenceLog> findOne(String sentence, Long MemberId);

}
