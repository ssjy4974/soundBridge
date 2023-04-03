package com.soundbridge.domain.meeting.service;

import com.soundbridge.domain.board.repository.BoardRepository;
import com.soundbridge.domain.meeting.entity.Meeting;
import com.soundbridge.domain.meeting.repository.MeetingRepository;
import com.soundbridge.domain.meeting.repository.MeetingRoomRepository;
import com.soundbridge.domain.meeting.request.MeetingSaveReq;
import com.soundbridge.domain.meeting.response.MeetingDetailRes;
import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.entity.Role;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import com.soundbridge.global.error.exception.NotFoundException;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /**
     * 피드백 상담 생성
     *
     * @param req
     */
    public void saveMeeting(MeetingSaveReq req, Long applicantId) {
        final Member applicant = memberRepository.findById(applicantId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        final Member helper = memberRepository.findById(req.getHelperId()).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        // 수락을 누른 대상의 역할이 봉사자가 아닌 경우 권한 없음 예외 발생
        if (!applicant.getRole().equals(Role.HELPER)) {
            throw new AccessDeniedException(ErrorCode.NOT_AUTHORIZATION);
        }
        String code = createCode();
        final Meeting meeting = req.toEntity(helper, applicant, code);

        meetingRepository.save(meeting);

        // 해당 상당 요청에 대한 수락이 완료되면, 상당 요청 글 삭제
        boardRepository.findById(req.getFeedbackBoardId()).orElseThrow(() ->
            new NotFoundException(ErrorCode.FEEDBACK_BOARD_NOT_FOUND));

        boardRepository.deleteById(req.getFeedbackBoardId());

    }

    /**
     * 상담 전체 조회 페이징
     *
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public Slice<MeetingDetailRes> findAllWithPaging(Pageable pageable, Long cursorId,
        Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        return meetingRepository.findAll(pageable, cursorId, memberId, member.getRole());
    }

    /**
     * 상담 상세 조회
     *
     * @param meetingId
     * @return
     */
    @Transactional(readOnly = true)
    public MeetingDetailRes findMeeting(Long meetingId) {
        return meetingRepository.findOne(meetingId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEETING_NOT_FOUND));
    }

    // 해당 방 입장 코드를 uuid로 생성
    private String createCode() {
        return UUID.randomUUID().toString();
    }

}
