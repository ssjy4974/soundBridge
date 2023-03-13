package com.soundbridge.domain.member.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.response.MemberInfoRes;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberInfoRes getMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberInfoRes.of(member.getId(), member.getEmail(), member.getNickname()
            , member.getProfile(), member.getRole());
    }
}
