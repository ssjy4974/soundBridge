package com.soundbridge.domain.sentence.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.sentence.entity.Category;
import com.soundbridge.domain.sentence.entity.MySentenceLog;
import com.soundbridge.domain.sentence.repository.CategoryRepository;
import com.soundbridge.domain.sentence.repository.MySentenceLogRepository;
import com.soundbridge.domain.sentence.request.CategoryReq;
import com.soundbridge.domain.sentence.request.CategoryUpdateReq;
import com.soundbridge.domain.sentence.request.MySentenceLogReq;
import com.soundbridge.domain.sentence.response.CategoryRes;
import com.soundbridge.domain.sentence.response.MySentenceLogRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    /**
     * 내가 입력한 카테고리 저장
     *
     * @param req
     * @param memberId
     */
    @Transactional
    public List<CategoryRes> saveCategoryName(CategoryReq req, Long memberId) {
        final Member getMember = memberRepository.findById(memberId)
            .orElseThrow(() -> new NotFoundException(
                ErrorCode.MEMBER_NOT_FOUND));

        final Category category = req.toEntity(req.getCategoryName(), getMember);
        categoryRepository.save(category);

        return categoryRepository.findByCategoriesByMemberId(memberId);
    }

    /**
     * 카테고리 조회
     *
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public List<CategoryRes> getCategoryList(Long memberId) {
        return categoryRepository.findByCategoriesByMemberId(memberId);
    }

    /**
     * 카테고리 삭제
     *
     * @param categoryId
     * @param memberId
     * @return
     */
    @Transactional
    public List<CategoryRes> deleteCategory(Long categoryId, Long memberId) {
        categoryRepository.deleteById(categoryId);
        return categoryRepository.findByCategoriesByMemberId(memberId);
    }

    /**
     * 카테고리 수정
     *
     * @param req
     * @param memberId
     * @return
     */
    @Transactional
    public List<CategoryRes> updateCategoryName(CategoryUpdateReq req, Long memberId) {
        Category updateCategory = categoryRepository.findById(req.getCategoryId()).orElseThrow();
        updateCategory.updateCategoryName(req.getCategoryName());

        return categoryRepository.findByCategoriesByMemberId(memberId);

    }

}

