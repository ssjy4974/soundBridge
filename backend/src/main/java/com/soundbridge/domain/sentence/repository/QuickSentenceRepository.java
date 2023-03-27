package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.QuickSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickSentenceRepository extends JpaRepository<QuickSentence, Long>,
    QuickSentenceRepositorySupport {

}
