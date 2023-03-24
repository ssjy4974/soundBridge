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
public class CategoryReq {

    @NotNull(message = "category name cannot be null.")
    @Size(max = 10, message = "The maximum length of the category name is 10.")
    private String categoryName;

    public Category toEntity(String categoryName, Member member) {
        return Category
            .builder()
            .member(member)
            .categoryName(categoryName)
            .build();
    }

}
