package com.soundbridge.domain.record.service;

import com.soundbridge.domain.record.entity.RecordSentence;
import com.soundbridge.domain.record.entity.RecordState;
import com.soundbridge.domain.record.repository.RecordSentenceRepository;
import com.soundbridge.domain.record.repository.RecordStateRepository;
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
@Transactional(readOnly = true)
public class RecordService {

    private final RecordStateRepository recordStateRepository;
    private final RecordSentenceRepository recordSentenceRepository;

    public void getMyNextRecord(Long memberId) {
        Long sentenceId = 1L;
        String content = "";

        Optional<RecordState> recordState = recordStateRepository.findById(memberId);
        if (!recordState.isEmpty()) {
            sentenceId = recordState.get().getId();
        }

        RecordSentence recordSentence = recordSentenceRepository.findById(sentenceId).get();


//        content =
    }
}
