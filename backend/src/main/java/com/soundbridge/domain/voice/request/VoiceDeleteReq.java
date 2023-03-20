package com.soundbridge.domain.voice.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoiceDeleteReq {
    private Long memberId;
    private Long voiceId;
}
