package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.Category;
import com.soundbridge.domain.sentence.response.CategoryRes;
import com.soundbridge.domain.sentence.response.QuickSentenceRes;
import java.util.List;
import java.util.Optional;

public interface QuickSentenceRepositorySupport {

    List<QuickSentenceRes> findBySentencesByCategoryId(Long categoryId);

}
