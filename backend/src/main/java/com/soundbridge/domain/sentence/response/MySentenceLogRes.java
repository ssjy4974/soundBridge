package com.soundbridge.domain.sentence.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MySentenceLogRes {

    private Long mySentenceLogId;
    private String sentence;

    @QueryProjection
    public MySentenceLogRes(Long mySentenceLogId, String sentence) {
        this.mySentenceLogId = mySentenceLogId;
        this.sentence = sentence;
    }

}
