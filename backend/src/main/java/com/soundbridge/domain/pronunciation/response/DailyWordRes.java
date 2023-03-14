package com.soundbridge.domain.pronunciation.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DailyWordRes {

    private Long dailyWordId;
    private String word;
    private String guidWord;
    private int tryCount;
    private int successCount;

    @QueryProjection
    public DailyWordRes(Long dailyWordId, String word, String guidWord, int tryCount, int successCount){
        this.dailyWordId = dailyWordId;
        this.word = word;
        this.guidWord = guidWord;
        this.tryCount = tryCount;
        this.successCount = successCount;
    }
}
