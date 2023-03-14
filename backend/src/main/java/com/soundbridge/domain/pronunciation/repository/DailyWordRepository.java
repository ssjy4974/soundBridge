package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.entity.DailyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyWordRepository extends JpaRepository<DailyWord, Long>,
    DailyWordRepositorySupport {

}
