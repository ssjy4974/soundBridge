package com.soundbridge.domain.meeting.service;

import com.soundbridge.domain.meeting.entity.Meeting;
import com.soundbridge.domain.meeting.repository.MeetingRepository;
import com.soundbridge.domain.meeting.repository.MeetingRoomRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingRoomService {
    private final MeetingRepository meetingRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    /**
     * 미팅 룸 생성
     * @param meetingId
     * @throws OpenViduJavaClientException
     * @throws OpenViduHttpException
     */
    public void createRoom(Long meetingId)
        throws OpenViduJavaClientException, OpenViduHttpException {
        final Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEETING_NOT_FOUND));

        meetingRoomRepository.create(meeting.getCode());
        meeting.startMeeting();
    }
}
