package com.soundbridge.domain.board.repository;

import com.soundbridge.domain.board.entity.FeedbackBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<FeedbackBoard, Long>,
    BoardRepositorySupport {

}
