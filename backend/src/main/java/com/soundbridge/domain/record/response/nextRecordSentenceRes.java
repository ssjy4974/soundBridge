package com.soundbridge.domain.record.response;

import lombok.Builder;

public class nextRecordSentenceRes {

    private Long sentenceId;
    private String content;

    @Builder
    public nextRecordSentenceRes(Long sentenceId, String content) {
        this.sentenceId = sentenceId;
        this.content = content;
    }

    public static nextRecordSentenceRes of(Long sentenceId, String content) {
        return nextRecordSentenceRes.builder()
            .sentenceId(sentenceId)
            .content(content)
            .build();
    }

}
