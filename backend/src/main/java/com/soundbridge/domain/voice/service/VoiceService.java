package com.soundbridge.domain.voice.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.voice.repository.VoiceRepository;
import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.response.VoiceDetailRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class VoiceService {

    private final MemberRepository memberRepository;
    private final VoiceRepository voiceRepository;

    public Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId,
        VoiceListConditionReq voiceListConditionReq, Long memberId) {

        log.info("voiceListConditionReq {}", voiceListConditionReq.toString());
//        final Member member = memberRepository.findById(memberId)
//            .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return voiceRepository.findAllVoiceWithPaging(pageable, cursorId, voiceListConditionReq);
    }
}
