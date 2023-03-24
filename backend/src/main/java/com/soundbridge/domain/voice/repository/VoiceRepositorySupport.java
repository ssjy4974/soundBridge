package com.soundbridge.domain.voice.repository;

import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.response.VoiceDetailRes;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface VoiceRepositorySupport {

    Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId,
        VoiceListConditionReq voiceListConditionReq);

    List<VoiceDetailRes> findMyVocieByMemberId(Long memberId);
}
