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
import java.util.Optional;
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

        Optional<RecordState> recordState = recordStateRepository.findByMemberId(memberId);
//        log.info("recordState {}", recordState);
        if (recordState.isPresent()) {
            sentenceId = recordState.get().getRecordSentence().getId();
            content = recordState.get().getRecordSentence().getContent();
        } else {
            content = recordSentenceRepository.findById(sentenceId).get().getContent();
        }
        log.info("record_state m_id:{}, s_id:{}, {}", memberId, sentenceId, content);

        return NextRecordSentenceRes.of(sentenceId, content);

//        content =
    }

    public void startRecord(Long memberId) {
        Long sentenceId = 1L;
        if(recordStateRepository.findByMemberId(memberId).isPresent()){
            return;
        }
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        RecordSentence recordSentence = recordSentenceRepository.findById(sentenceId).get();

        RecordState recordState = RecordState.builder().member(member)
                .recordSentence(recordSentence)
                .build();
        recordStateRepository.save(recordState);
    }

    public void deleteRecord(Long memberId){
        RecordState recordState = recordStateRepository.findByMemberId(memberId).get();
        recordStateRepository.delete(recordState);
    }
}
