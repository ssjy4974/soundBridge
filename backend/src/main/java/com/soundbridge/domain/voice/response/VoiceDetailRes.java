package com.soundbridge.domain.voice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoiceDetailRes {
    private Long voiceId;
    private int age;
    private String modelUrl;
    private String voiceGender;
    private String voiceName;
    private Long memberId;
}
