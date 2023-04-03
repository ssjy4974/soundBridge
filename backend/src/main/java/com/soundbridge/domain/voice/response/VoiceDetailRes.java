package com.soundbridge.domain.voice.response;

import com.querydsl.core.annotations.QueryProjection;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VoiceDetailRes {

    private Long voiceId;
    private int age;
    private String voiceGender;
    private String voiceName;
    private Long memberId;
    private String profile;
    private List<FeatureRes> featureResList;

    @QueryProjection
    public VoiceDetailRes(Long voiceId, int age, String voiceGender,
        String voiceName, Long memberId, String profile, List<FeatureRes> featureResList) {
        this.voiceId = voiceId;
        this.age = age;
        this.voiceGender = voiceGender;
        this.voiceName = voiceName;
        this.memberId = memberId;
        this.profile = profile;
        this.featureResList = featureResList;
    }
}
