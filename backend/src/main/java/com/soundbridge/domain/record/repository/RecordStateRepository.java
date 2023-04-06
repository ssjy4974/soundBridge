package com.soundbridge.domain.record.repository;

import com.soundbridge.domain.record.entity.RecordState;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordStateRepository extends JpaRepository<RecordState, Long> {

    Optional<RecordState> findByMemberId(Long memberId);

}
