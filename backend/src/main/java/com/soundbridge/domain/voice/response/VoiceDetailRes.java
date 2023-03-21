package com.soundbridge.domain.voice.response;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
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
    private List<FeatureRes> featureResList;

    @QueryProjection
    public VoiceDetailRes(Long voiceId, int age, String modelUrl, String voiceGender,
        String voiceName, Long memberId, List<FeatureRes> featureResList) {
        this.voiceId = voiceId;
        this.age = age;
        this.modelUrl = modelUrl;
        this.voiceGender = voiceGender;
        this.voiceName = voiceName;
        this.memberId = memberId;
        this.featureResList = featureResList;
    }

    @Override
    public String toString() {
        return "VoiceDetailRes{" +
            "voiceId=" + voiceId +
            ", age=" + age +
            ", modelUrl='" + modelUrl + '\'' +
            ", voiceGender='" + voiceGender + '\'' +
            ", voiceName='" + voiceName + '\'' +
            ", memberId=" + memberId +
            ", featureResList=" + featureResList +
            '}';
    }
}
