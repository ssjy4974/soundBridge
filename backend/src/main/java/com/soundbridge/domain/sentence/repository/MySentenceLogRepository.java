package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.MySentenceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MySentenceLogRepository extends JpaRepository<MySentenceLog, Long>,
    MySentenceLogRepositorySupport {

}
