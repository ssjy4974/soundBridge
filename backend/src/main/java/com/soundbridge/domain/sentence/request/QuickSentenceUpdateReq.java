package com.soundbridge.domain.sentence.request;

import com.soundbridge.domain.sentence.entity.Category;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QuickSentenceUpdateReq {

    @NotNull(message = "quickSentenceId cannot be null.")
    private Long quickSentenceId;

    @NotNull(message = "quickSentence cannot be null.")
    private String quickSentence;

}
