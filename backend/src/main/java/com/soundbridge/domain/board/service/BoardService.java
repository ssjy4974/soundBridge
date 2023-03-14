package com.soundbridge.domain.board.service;

import com.soundbridge.domain.board.entity.FeedbackBoard;
import com.soundbridge.domain.board.repository.BoardRepository;
import com.soundbridge.domain.board.request.BoardSaveReq;
import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
     * @param req
     * @param memberId
     */
    public void saveFeedbackBoard(BoardSaveReq req, Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        final FeedbackBoard feedbackBoard = req.toEntity(member);

        boardRepository.save(feedbackBoard);
    }
}
