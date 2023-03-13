package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.entity.TryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryHistoryRepository extends JpaRepository<TryHistory, Long>,
    TryHistoryRepositorySupport {

}
