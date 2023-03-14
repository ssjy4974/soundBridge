package com.soundbridge.domain.pronunciation.service;

import com.soundbridge.domain.pronunciation.repository.DailyWordRepository;
import com.soundbridge.domain.pronunciation.response.DailyWordRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DailyWordService {

    private final DailyWordRepository dailyWordRepository;

    /**
     * 일상 단어 전체 조회
     *
     * @param memberId
     * @return
     */
    public List<DailyWordRes> findAllDailyWords(Long memberId) {
        return dailyWordRepository.findAllByMemberId(memberId);
    }
}
