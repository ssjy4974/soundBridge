package com.soundbridge.domain.record.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NextRecordSentenceRes {

    private Long sentenceId;
    private String content;

    @Builder
    public NextRecordSentenceRes(Long sentenceId, String content) {
        this.sentenceId = sentenceId;
        this.content = content;
    }

    public static NextRecordSentenceRes of(Long sentenceId, String content) {
        return NextRecordSentenceRes.builder()
            .sentenceId(sentenceId)
            .content(content)
            .build();
    }

}
