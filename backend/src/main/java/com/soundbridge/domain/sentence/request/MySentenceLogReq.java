package com.soundbridge.domain.sentence.request;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.sentence.entity.MySentenceLog;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MySentenceLogReq {

    @NotNull(message = "sentence cannot be null.")
    @Size(max = 200, message = "The maximum length of the sentence is 200.")
    private String sentence;

    public MySentenceLog toEntity(String sentence, Member member) {
        return MySentenceLog
            .builder()
            .member(member)
            .sentenceLog(sentence)
            .build();
    }

}
