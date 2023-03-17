package com.soundbridge.domain.voice.service;

import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.response.VoiceDetailRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
public class VoiceService {

    public Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId, VoiceListConditionReq voiceListConditionReq) {
        return null;
    }
}
