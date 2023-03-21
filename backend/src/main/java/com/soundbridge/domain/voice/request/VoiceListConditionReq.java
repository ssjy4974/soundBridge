package com.soundbridge.domain.voice.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoiceListConditionReq {
    private Integer voiceAge;
    private String voiceGender;
    private List<Long> features;

    @Override
    public String toString() {
        return "VoiceListConditionReq{" +
            "voiceAge=" + voiceAge +
            ", voiceGender='" + voiceGender + '\'' +
            ", features=" + features +
            '}';
    }
}
