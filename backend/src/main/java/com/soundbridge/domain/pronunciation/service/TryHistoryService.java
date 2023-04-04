package com.soundbridge.domain.pronunciation.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.pronunciation.entity.BasicLetter;
import com.soundbridge.domain.pronunciation.entity.DailyWord;
import com.soundbridge.domain.pronunciation.entity.PronunciationType;
import com.soundbridge.domain.pronunciation.entity.TryHistory;
import com.soundbridge.domain.pronunciation.entity.WordMember;
import com.soundbridge.domain.pronunciation.repository.BasicLetterRepository;
import com.soundbridge.domain.pronunciation.repository.DailyWordRepository;
import com.soundbridge.domain.pronunciation.repository.TryHistoryRepository;
import com.soundbridge.domain.pronunciation.repository.WordMemberRepository;
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
    private final WordMemberRepository wordMemberRepository;
    private final DailyWordRepository dailyWordRepository;
    private final TryHistoryRepository tryHistoryRepository;
    private final MemberRepository memberRepository;

    /**
     * 기본 발음 연습 , 시도횟수 업데이트
     *
     * @param memberId
     * @param basicLetterId
     */
    public void saveOrUpdateByBasicLetter(Long basicLetterId, Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        final BasicLetter basicLetter = basicLetterRepository.findById(basicLetterId)
            .orElseThrow(() ->
                new NotFoundException(ErrorCode.BASIC_LETTER_NOT_FOUND));

        final Optional<TryHistory> getTryHistory = tryHistoryRepository.findByBasicLetterIdAndMemberId(
            basicLetterId,
            memberId);

        // 이미 존재 하는 경우 업데이트 로직 수행
        if (getTryHistory.isPresent()) {
            getTryHistory.get().increaseTryCount();
        } else { // 없으면 save 로직 수행
            final TryHistory tryHistory = TryHistory.builder()
                .member(member)
                .basicLetter(basicLetter)
                .type(PronunciationType.BASIC_LETTER)
                .build();

            tryHistoryRepository.save(tryHistory);
        }
    }

    /**
     * 일상 단어 발음 연습 , 시도횟수 업데이트
     *
     * @param wordMemberId
     * @param memberId
     */
    public void saveOrUpdateBywordMemberId(Long wordMemberId, Long memberId) {
        final Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        final WordMember wordMember = wordMemberRepository.findById(wordMemberId)
            .orElseThrow(() ->
                new NotFoundException(ErrorCode.WORDMEMBER_NOT_FOUND));

        final Optional<TryHistory> getTryHistory = tryHistoryRepository.findBywordMemberId(
            wordMemberId);

        // 이미 존재 하는 경우 업데이트 로직 수행
        if (getTryHistory.isPresent()) {
            getTryHistory.get().increaseTryCount();
        } else { // 없으면 save 로직 수행
            final TryHistory tryHistory = TryHistory.builder()
                .member(member)
                .wordMember(wordMember)
                .type(PronunciationType.DAILY_WORD)
                .build();

            tryHistoryRepository.save(tryHistory);
        }
    }

    /**
     * 기본 발음 성공 카운트 업데이트
     *
     * @param basicLetterId
     * @param memberId
     */
    public void updateByBasicLetter(Long basicLetterId, Long memberId) {

        basicLetterRepository.findById(basicLetterId)
            .orElseThrow(() ->
                new NotFoundException(ErrorCode.BASIC_LETTER_NOT_FOUND));

        TryHistory tryHistory = tryHistoryRepository.findByBasicLetterIdAndMemberId(
            basicLetterId, memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.TRY_HISTORY_NOT_FOUND));

        tryHistory.increaseSuccessCount();

    }

    /**
     * 일상 단어 성공 업데이트
     *
     * @param wordMemberId
     */
    public void updateBywordMemberId(Long wordMemberId) {

        final WordMember wordMember = wordMemberRepository.findById(wordMemberId)
            .orElseThrow(() ->
                new NotFoundException(ErrorCode.WORDMEMBER_NOT_FOUND));

        TryHistory tryHistory = tryHistoryRepository.findBywordMemberId(
            wordMemberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.TRY_HISTORY_NOT_FOUND));

        tryHistory.increaseSuccessCount();

    }

    public void deleteByWordMemberId(Long wordMemberId) {

        Optional<TryHistory> tryHistory = tryHistoryRepository.findBywordMemberId(
            wordMemberId);

        if(tryHistory.isPresent()){
            tryHistoryRepository.delete(tryHistory.get());
        }else{
            final WordMember wordMember = wordMemberRepository.findById(wordMemberId)
                .orElseThrow(() ->
                    new NotFoundException(ErrorCode.WORDMEMBER_NOT_FOUND));
            wordMemberRepository.delete(wordMember);
        }
    }

}
