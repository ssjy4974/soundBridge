package com.soundbridge.domain.voice.entity;


import com.soundbridge.domain.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Voice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voice_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_vocie_member_idx"))
    private Member member;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    private int age;

    @Column(length = 200, nullable = false)
    private String voiceName;


    @Column(length = 200, nullable = false)
    private String modelUrl;

    @Column(length = 10, nullable = false)
    private String voiceGender;


    @Builder
    public Voice(Member member, int age, String voiceName, String modelUrl, String voiceGender) {
        this.member = member;
        this.age = age;
        this.voiceName = voiceName;
        this.modelUrl = modelUrl;
        this.voiceGender = voiceGender;
    }
}
