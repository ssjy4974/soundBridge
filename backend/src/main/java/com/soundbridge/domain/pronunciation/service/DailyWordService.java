package com.soundbridge.domain.pronunciation.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.pronunciation.repository.DailyWordRepository;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyWordService {

    private final DailyWordRepository dailyWordRepository;
    private final MemberRepository memberRepository;

    /**
     * 일상 단어 상세 조회
     *
     * @param dailyWord
     * @return
     */
//    public List<DailyWordRes> findDailyWord(String dailyWord, Long memberId) {
//        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
//            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
//
//        return dailyWordRepository.findDailyWordBydailyWord(dailyWord);
//    }
}
