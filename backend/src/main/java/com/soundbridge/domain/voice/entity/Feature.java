package com.soundbridge.domain.voice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @Column(length = 10, name = "feature_name", nullable = false)
    private String featureName;

    @Builder
    public Feature(String featureName) {
        this.featureName = featureName;
    }
}
