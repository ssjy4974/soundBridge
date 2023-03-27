package com.soundbridge.domain.sentence.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.sentence.entity.Category;
import com.soundbridge.domain.sentence.entity.QuickSentence;
import com.soundbridge.domain.sentence.repository.CategoryRepository;
import com.soundbridge.domain.sentence.repository.QuickSentenceRepository;
import com.soundbridge.domain.sentence.request.CategoryReq;
import com.soundbridge.domain.sentence.request.CategoryUpdateReq;
import com.soundbridge.domain.sentence.request.QuickSentenceReq;
import com.soundbridge.domain.sentence.request.QuickSentenceUpdateReq;
import com.soundbridge.domain.sentence.response.CategoryRes;
import com.soundbridge.domain.sentence.response.QuickSentenceRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuickSentenceService {

    private final CategoryRepository categoryRepository;
    private final QuickSentenceRepository quickSentenceRepository;
    private final MemberRepository memberRepository;

    /**
     * 내가 입력한 자주 사용하는 문장 저장
     *
     * @param req
     * @return
     */
    @Transactional
    public List<QuickSentenceRes> saveQuickSentence(QuickSentenceReq req) {
        final Category category = categoryRepository.findById(req.getCategoryId())
            .orElseThrow();

        final QuickSentence quickSentence = req.toEntity(req.getSentence(), category);
        quickSentenceRepository.save(quickSentence);

        return quickSentenceRepository.findBySentencesByCategoryId(req.getCategoryId());
    }

    /**
     * 카테고리 별 자주 사용하는 문장 조회
     *
     * @param categoryId
     * @return
     */
    @Transactional(readOnly = true)
    public List<QuickSentenceRes> getQuickSentencesList(Long categoryId) {
        return quickSentenceRepository.findBySentencesByCategoryId(categoryId);
    }

    /**
     * 자주 사용하는 문장 삭제
     *
     * @param quickSentenceId
     * @return
     */
    @Transactional
    public List<QuickSentenceRes> deleteQuickSentence(Long quickSentenceId) {
        QuickSentence quickSentence = quickSentenceRepository.findById(quickSentenceId).get();
        quickSentenceRepository.deleteById(quickSentenceId);
        return quickSentenceRepository.findBySentencesByCategoryId(quickSentence.getCategory().getId());
    }

    /**
     * 자주 사용하는 문장 수정
     *
     * @param req
     * @return
     */
    @Transactional
    public List<QuickSentenceRes> updateQuickSentence(QuickSentenceUpdateReq req) {
        QuickSentence quickSentence = quickSentenceRepository.findById(req.getQuickSentenceId()).orElseThrow();
        quickSentence.updateQuickSentence(req.getQuickSentence());

        return quickSentenceRepository.findBySentencesByCategoryId(quickSentence.getCategory().getId());

    }

    /**
     * 자주 사용하는 문장, 카테고리 count
     *
     * @param quickSentenceId
     * @return
     */
    @Transactional
    public List<QuickSentenceRes> updateCount(Long quickSentenceId) {
        QuickSentence quickSentence = quickSentenceRepository.findById(quickSentenceId).orElseThrow();
        quickSentence.getCategory().increaseCount();
        quickSentence.increaseCount();

        return quickSentenceRepository.findBySentencesByCategoryId(quickSentence.getCategory().getId());
    }
}

