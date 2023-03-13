package com.soundbridge.domain.sentence.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.sentence.entity.MySentenceLog;
import com.soundbridge.domain.sentence.repository.MySentenceLogRepository;
import com.soundbridge.domain.sentence.request.MySentenceLogReq;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MySentenceLogService {

    private final MySentenceLogRepository mySentenceLogRepository;
    private final MemberRepository memberRepository;

    /**
     * 내가 입력한 문장 저장,업데이트
     *
     * @param req
     * @param memberId
     */
    public void saveOrUpdateMySentenceLog(MySentenceLogReq req, Long memberId) {
        final Member getMember = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.MEMBER_NOT_FOUND));

        Optional<MySentenceLog> getMySentenceLog = mySentenceLogRepository.findOne(
            req.getSentence(), memberId);

        if (getMySentenceLog.isEmpty()) {
            final MySentenceLog mySentenceLog = req.toEntity(req.getSentence(), getMember);
            mySentenceLogRepository.save(mySentenceLog);
        } else {
            getMySentenceLog.get().increaseCount();
        }

    }
}
