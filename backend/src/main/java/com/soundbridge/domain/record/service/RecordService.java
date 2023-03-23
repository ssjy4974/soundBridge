package com.soundbridge.domain.record.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.record.entity.RecordSentence;
import com.soundbridge.domain.record.entity.RecordState;
import com.soundbridge.domain.record.repository.RecordSentenceRepository;
import com.soundbridge.domain.record.repository.RecordStateRepository;
import com.soundbridge.domain.record.response.NextRecordSentenceRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class RecordService {

    private final RecordStateRepository recordStateRepository;
    private final RecordSentenceRepository recordSentenceRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public NextRecordSentenceRes getMyNextRecord(Long memberId) {
        Long sentenceId = 1L;
        String content = "";

        RecordState recordState = recordStateRepository.findByMemberId(memberId);
//        log.info("recordState {}", recordState);
        if (recordState != null) {
            sentenceId = recordState.getRecordSentence().getId();
            content = recordState.getRecordSentence().getContent();
        } else {
            content = recordSentenceRepository.findById(sentenceId).get().getContent();
        }
        log.info("record_state m_id:{}, s_id:{}, {}", memberId, sentenceId, content);

        return NextRecordSentenceRes.of(sentenceId, content);

//        content =
    }

    public void startRecord(Long memberId) {
        Long sentenceId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        RecordSentence recordSentence = recordSentenceRepository.findById(sentenceId).get();

        RecordState recordState = RecordState.builder().member(member)
                .recordSentence(recordSentence)
                .build();
        System.out.println("---------------save---------------");
        recordStateRepository.save(recordState);
    }
}
