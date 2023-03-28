package com.soundbridge.domain.voice.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.voice.entity.Voice;
import com.soundbridge.domain.voice.repository.VoiceRepository;
import com.soundbridge.domain.voice.request.VoiceDeleteReq;
import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.request.VoiceSelectionReq;
import com.soundbridge.domain.voice.response.VoiceDetailRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class VoiceService {

    private final MemberRepository memberRepository;
    private final VoiceRepository voiceRepository;

    @Transactional(readOnly = true)
    public Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId,
        VoiceListConditionReq voiceListConditionReq, Long memberId) {

        log.info("voiceListConditionReq {}", voiceListConditionReq.toString());
//        final Member member = memberRepository.findById(memberId)
//            .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return voiceRepository.findAllVoiceWithPaging(pageable, cursorId, voiceListConditionReq);
    }


    public List<VoiceDetailRes> findMyVocieByMemberId(Long memberId) {
        log.info("voiceListConditionReq {}", memberId.toString());
        return voiceRepository.findMyVocieByMemberId(memberId);
    }

    public void selectByVoiceId(Long memberId, VoiceSelectionReq voiceSelectionReq) {
        log.info("select voice m:{}, v:{}", voiceSelectionReq.getMemberId(),
            voiceSelectionReq.getVoiceId());
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        if(!voiceSelectionReq.getMemberId().equals(member.getId())) {
            throw new AccessDeniedException(ErrorCode.NOT_AUTHORIZATION);
        }

        member.selectVoice(voiceRepository.findById(voiceSelectionReq.getVoiceId()).get());
    }

    public void deleteVoiceById(long memberId, VoiceDeleteReq voiceDeleteReq) {
        if(memberId != voiceDeleteReq.getMemberId()) {
            throw new AccessDeniedException(ErrorCode.NOT_AUTHORIZATION);
        }
        log.info("Voice Delete {}", voiceDeleteReq.getVoiceId());

        Optional<Voice> voice = voiceRepository.findById(voiceDeleteReq.getVoiceId());

        if(!voice.isEmpty()) {
            memberRepository.MemberVoiceDelete(voice.get());
        }

        voiceRepository.deleteById(voiceDeleteReq.getVoiceId());
    }

}
