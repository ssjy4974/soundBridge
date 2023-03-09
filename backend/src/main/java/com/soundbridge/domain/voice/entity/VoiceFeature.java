package com.soundbridge.domain.voice.entity;

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
public class VoiceFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voice_feature_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voice_id", nullable = false, foreignKey = @ForeignKey(name = "fk_voice_feature_voice_idx"))
    private Voice voice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id", nullable = false, foreignKey = @ForeignKey(name = "fk_voice_feature_feature_idx"))
    private Feature feature;

    @Builder
    public VoiceFeature(Voice voice, Feature feature) {
        this.voice = voice;
        this.feature = feature;
    }
}
