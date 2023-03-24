package com.soundbridge.domain.voice.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VoiceSelectionReq {
    private Long memberId;
    private Long voiceId;
}
