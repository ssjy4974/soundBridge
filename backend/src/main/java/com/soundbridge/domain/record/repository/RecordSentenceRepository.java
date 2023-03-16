package com.soundbridge.domain.record.repository;

import com.soundbridge.domain.record.entity.RecordSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordSentenceRepository extends JpaRepository<RecordSentence, Long> {

}
