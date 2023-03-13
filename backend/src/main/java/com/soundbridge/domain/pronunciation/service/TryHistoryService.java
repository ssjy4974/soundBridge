package com.soundbridge.domain.pronunciation.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.pronunciation.entity.BasicLetter;
import com.soundbridge.domain.pronunciation.entity.PronunciationType;
import com.soundbridge.domain.pronunciation.entity.TryHistory;
import com.soundbridge.domain.pronunciation.repository.BasicLetterRepository;
import com.soundbridge.domain.pronunciation.repository.DailyWordRepository;
import com.soundbridge.domain.pronunciation.repository.TryHistoryRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TryHistoryService {

    private final BasicLetterRepository basicLetterRepository;
    private final DailyWordRepository dailyWordRepository;
    private final TryHistoryRepository tryHistoryRepository;
    private final MemberRepository memberRepository;

}
