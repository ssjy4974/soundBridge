package com.soundbridge.domain.voice.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureRes {

    private Long featureId;
    private String featureName;

    @QueryProjection
    public FeatureRes(Long featureId, String featureName) {
        this.featureId = featureId;
        this.featureName = featureName;
    }

    @Override
    public String                                     toString() {
        return "FeatureRes{" +
            "featureId=" + featureId +
            ", featureName='" + featureName + '\'' +
            '}';
    }
}
