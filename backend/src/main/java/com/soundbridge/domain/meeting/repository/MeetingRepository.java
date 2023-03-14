package com.soundbridge.domain.meeting.repository;

import com.soundbridge.domain.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>,
    MeetingRepositorySupport {

}
