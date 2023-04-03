package com.soundbridge.domain.board.service;

import com.soundbridge.domain.board.entity.FeedbackBoard;
import com.soundbridge.domain.board.repository.BoardRepository;
import com.soundbridge.domain.board.request.BoardSaveReq;
import com.soundbridge.domain.board.response.BoardDetailRes;
import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.entity.Role;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import com.soundbridge.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 등록
     *
     * @param req
     * @param memberId
     */
    public void saveFeedbackBoard(BoardSaveReq req, Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        final FeedbackBoard feedbackBoard = req.toEntity(member);

        boardRepository.save(feedbackBoard);
    }

    /**
     * 상담 게시글 조회
     *
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public Slice<BoardDetailRes> findAllWithPaging(Pageable pageable, Long cursorId,
        Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        if (Role.HELPER.equals(member.getRole())) {
            memberId = null;
        }
        return boardRepository.findAllWithPaging(pageable, cursorId, memberId);
    }

    /**
     * 게시글 삭제
     *
     * @param feedbackBoardId
     * @param memberId
     */
    public void deleteBoard(Long feedbackBoardId, Long memberId) {
        final FeedbackBoard feedbackBoard = boardRepository.findById(feedbackBoardId)
            .orElseThrow(() ->
                new NotFoundException(ErrorCode.FEEDBACK_BOARD_NOT_FOUND));

        checkValidation(feedbackBoardId, memberId);

        boardRepository.deleteById(feedbackBoardId);
    }

    /**
     * 삭제, 수정 검증
     * @param feedbackBoardId
     * @param memberId
     */
    @Transactional(readOnly = true)
    public void checkValidation(Long feedbackBoardId, Long memberId) {
        // 삭제 대상이 되는 feedbackBoard 존재 여부
        final FeedbackBoard feedbackBoard = boardRepository.findById(feedbackBoardId)
            .orElseThrow(() ->
                new NotFoundException(ErrorCode.FEEDBACK_BOARD_NOT_FOUND));
        // 요청에서 넘어온 memberId와, 해당 feedbackBoard의 작성자 id가 같은지 검증
        if (feedbackBoard.getMember().getId() != memberId) {
            throw new AccessDeniedException(ErrorCode.NOT_AUTHORIZATION);
        }

    }
}
