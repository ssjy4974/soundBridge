package com.soundbridge.domain.voice.request;

import java.util.List;
import lombok.Getter;

@Getter
public class VoiceListConditionReq {
    private int voiceAge;
    private String gender;
    private List<Integer> features;
}
