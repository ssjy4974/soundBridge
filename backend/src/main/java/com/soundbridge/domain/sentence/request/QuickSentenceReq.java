package com.soundbridge.domain.sentence.request;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.sentence.entity.Category;
import com.soundbridge.domain.sentence.entity.QuickSentence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QuickSentenceReq {

    @NotNull(message = "category name cannot be null.")
    @Size(max = 30, message = "The maximum length of the category name is 30.")
    private String sentence;

    @NotNull(message = "categoryId cannot be null.")
    private Long categoryId;

    public QuickSentence toEntity(String sentence, Category category) {
        return QuickSentence
            .builder()
            .category(category)
            .sentence(sentence)
            .build();
    }

}
