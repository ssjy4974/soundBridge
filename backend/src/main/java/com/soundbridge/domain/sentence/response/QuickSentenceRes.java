package com.soundbridge.domain.sentence.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QuickSentenceRes {

    private Long quickSentenceId;
    private String sentence;

    @QueryProjection
    public QuickSentenceRes(Long quickSentenceId, String sentence) {
        this.quickSentenceId = quickSentenceId;
        this.sentence = sentence;
    }

}
