package com.soundbridge.domain.pronunciation.service;

import com.soundbridge.domain.pronunciation.repository.BasicLetterRepository;
import com.soundbridge.domain.pronunciation.response.BasicLetterRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicLetterService {

    private final BasicLetterRepository basicLetterRepository;

    /**
     * 기본 발음 전체 조회
     *
     * @param memberId
     * @return
     */
    public List<BasicLetterRes> findAllBasicLetters(Long memberId) {
        return basicLetterRepository.findAllByMemberId(memberId);
    }

    /**
     * 기본 발음 상세 조회
     *
     * @param basicLetterId
     * @return
     */
    public BasicLetterRes findBasicLetter(Long basicLetterId, Long memberId) {
        return basicLetterRepository.findOne(basicLetterId, memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.BASIC_LETTER_NOT_FOUND));
    }
}
