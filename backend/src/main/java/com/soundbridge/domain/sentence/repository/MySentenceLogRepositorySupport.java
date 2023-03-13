package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.MySentenceLog;
import java.util.Optional;

public interface MySentenceLogRepositorySupport {

    Optional<MySentenceLog> findOne(String sentence, Long MemberId);

}
