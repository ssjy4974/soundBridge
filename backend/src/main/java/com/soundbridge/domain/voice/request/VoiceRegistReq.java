package com.soundbridge.domain.voice.request;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.voice.entity.Voice;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoiceRegistReq {
    private String voiceName;
    private String modelUrl;
    private Integer voiceAge;
    private String voiceGender;
    private List<Long> features;

    public Voice toEntity(Member member){
        return Voice.builder()
                .voiceName(this.voiceName)
                .modelUrl(this.modelUrl)
                .voiceAge(this.voiceAge)
                .voiceGender(this.voiceGender)
                .member(member)
                .build();
    }
}
