package com.soundbridge.domain.meeting.service;

import com.soundbridge.domain.board.repository.BoardRepository;
import com.soundbridge.domain.meeting.entity.Meeting;
import com.soundbridge.domain.meeting.repository.MeetingRepository;
import com.soundbridge.domain.meeting.request.MeetingSaveReq;
import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.entity.Role;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
     * @param req
     */
    public void saveMeeting(MeetingSaveReq req) {
        final Member applicant = memberRepository.findById(req.getApplicantId()).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        final Member helper = memberRepository.findById(req.getHelperId()).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        // 수락을 누른 대상의 역할이 봉사자가 아닌 경우 권한 없음 예외 발생
        if (!applicant.getRole().equals(Role.APPLICANT)) {
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


    // 해당 방 입장 코드를 uuid로 생성
    private String createCode() {
        return UUID.randomUUID().toString();
    }

}
