package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.Category;
import com.soundbridge.domain.sentence.response.CategoryRes;
import java.util.List;
import java.util.Optional;

public interface CategoryRepositorySupport {

    List<CategoryRes> findByCategoriesByMemberId(Long memberId);

    Optional<Category> findCategory(String categoryName, Long MemberId);

}
