package com.soundbridge.domain.meeting.repository;

import com.soundbridge.domain.meeting.response.MeetingDetailRes;
import com.soundbridge.domain.member.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface MeetingRepositorySupport {

    Slice<MeetingDetailRes> findAll(Pageable pageable, Long cursorId, Long memberId, Role role);
}
