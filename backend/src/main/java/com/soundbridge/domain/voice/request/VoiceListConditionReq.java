package com.soundbridge.domain.voice.request;

import java.util.List;
import lombok.Getter;

@Getter
public class VoiceListConditionReq {
    private Integer voiceAge;
    private String voiceGender;
    private List<Integer> features;

    @Override
    public String toString() {
        return "VoiceListConditionReq{" +
            "voiceAge=" + voiceAge +
            ", voiceGender='" + voiceGender + '\'' +
            ", features=" + features +
            '}';
    }
}
