package com.soundbridge.domain.sentence.request;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.sentence.entity.Category;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryUpdateReq {

    @NotNull(message = "categoryId cannot be null.")
    private Long categoryId;

    @NotNull(message = "category name cannot be null.")
    @Size(max = 10, message = "The maximum length of the category name is 10.")
    private String categoryName;

    public Category toEntity(Long categoryId, String categoryName) {
        return Category
            .builder()
            .categoryId(categoryId)
            .categoryName(categoryName)
            .build();
    }

}
