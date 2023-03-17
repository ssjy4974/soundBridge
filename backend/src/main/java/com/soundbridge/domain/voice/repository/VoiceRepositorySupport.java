package com.soundbridge.domain.voice.repository;

import com.soundbridge.domain.voice.response.VoiceDetailRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface VoiceRepositorySupport {

    Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId, Long memberId);
}
