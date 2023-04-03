package com.soundbridge.domain.meeting.service;

import com.soundbridge.domain.meeting.entity.Meeting;
import com.soundbridge.domain.meeting.repository.MeetingRepository;
import com.soundbridge.domain.meeting.repository.MeetingRoomRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import com.soundbridge.global.error.exception.AlreadyExistResourceException;
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
     *
     * @param meetingId
     * @throws OpenViduJavaClientException
     * @throws OpenViduHttpException
     */
    public void createRoom(Long meetingId)
        throws OpenViduJavaClientException, OpenViduHttpException {
        final Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEETING_NOT_FOUND));

//        // 방이 이미 생성 된 경우
//        if (meeting.getOpenChk() == 1 ) {
//            throw new AlreadyExistResourceException(ErrorCode.ALREADY_EXIST_RESOURCE);
//        }
        
        // 이미 종료 된 상담인 경우
        if (meeting.getOpenChk() == 2 ) {
            throw new AccessDeniedException(ErrorCode.NOT_AUTHORIZATION);
        }

        meetingRoomRepository.create(meeting.getCode());
        meeting.startMeeting();
    }

    /**
     * 방 참가
     *
     * @param meetingId
     * @return
     * @throws OpenViduJavaClientException
     * @throws OpenViduHttpException
     */
    public String joinRoom(Long meetingId)
        throws OpenViduJavaClientException, OpenViduHttpException {
        final Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEETING_NOT_FOUND));

        if (meeting.getOpenChk() == 1) {
            throw new NotFoundException(ErrorCode.MEETING_ROOM_NOT_FOUND);
        }

        final String token = meetingRoomRepository.join(meeting.getCode());
        return token;
    }

    /**
     * 상담 종료
     *
     * @param meetingId
     */
    public void doneMeeting(Long meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEETING_NOT_FOUND));
        meeting.doneMeeting();
    }
}
