package com.soundbridge.domain.pronunciation.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DailyWordRes {

    private Long wordMemberId;
    private String word;
    private String guideWord;
    private int tryCount;
    private int successCount;

    @QueryProjection
    public DailyWordRes(Long wordMemberId, String word, String guideWord, int tryCount, int successCount){
        this.wordMemberId = wordMemberId;
        this.word = word;
        this.guideWord = guideWord;
        this.tryCount = tryCount;
        this.successCount = successCount;
    }
}
