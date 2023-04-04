package com.soundbridge.domain.member.service;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.request.SaveAddInfoReq;
import com.soundbridge.domain.member.response.MemberInfoRes;
import com.soundbridge.domain.voice.entity.Voice;
import com.soundbridge.domain.voice.repository.VoiceRepository;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.ImageExtensionException;
import com.soundbridge.global.error.exception.NotFoundException;
import com.soundbridge.global.service.AwsS3Service;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final VoiceRepository voiceRepository;
    private final AwsS3Service awsS3Service;

    private final RedisTemplate redisTemplate;

    @Transactional(readOnly = true)
    public MemberInfoRes getMemberById(Long memberId) {
        log.info("memberId {}", memberId);
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Long voiceId = (member.getVoice() == null) ? 0L : member.getVoice().getId();

        return MemberInfoRes.of(member.getId(), member.getEmail(), member.getNickname()
                , member.getProfile(), member.getRole(), voiceId);
    }

    @Transactional
    public void modifyMemberNickname(Long memberId, String nickname) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        if (!member.getNickname().equals(nickname)) {
            member.modifyNickname(nickname);
        }
    }

    @Transactional
    public String modifyMemberProfile(Long memberId, MultipartFile newProfile) {
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        List<String> contentType = new ArrayList<>();
        contentType.add("image/jpg");
        contentType.add("image/jpeg");
        contentType.add("image/png");

        if (contentType.contains(newProfile.getContentType())) {
            String filename = awsS3Service.multipartFileUpload(newProfile);
            if (!member.getProfile().equals("https://yeon-chung.s3.ap-northeast-2.amazonaws.com/default.png")) {
                awsS3Service.deleteObject(member.getProfile());
            }
//            String filename = newProfile.getOriginalFilename(); //s3 되면 바꾸기.
            member.modifyProfile(filename);

            return filename;
        } else {
            throw new ImageExtensionException(ErrorCode.EXTENSION_NOT_ALLOWED);
        }
    }

    @Transactional
    public String saveAddInfo(Long id, SaveAddInfoReq saveAddInfoReq) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Long defalutVoiceId = saveAddInfoReq.getGender().equals("M") ? 1L : 2L;
        Voice defalutVoice = voiceRepository.findById(defalutVoiceId).get();
        member.saveAddInfo(saveAddInfoReq.getAge(), saveAddInfoReq.getGender(),
                saveAddInfoReq.getRole(), defalutVoice);

        return member.getGender();
    }

    @Transactional
    public Cookie deleteMemberById(Long memberId, Cookie[] cookies) {
        Cookie refreshTokenCookie = deleteCookie(cookies);

        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        member.deleteMember();

        return refreshTokenCookie;
    }

    public Cookie logoutMemberById(Long memberId, Cookie[] cookies) {
        Cookie refreshTokenCookie = deleteCookie(cookies);

        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        redisTemplate.delete(member.getEmail());

        String refreshToken2 = (String) redisTemplate.opsForValue().get(member.getEmail());
//        if(!refreshToken2.equals(token.getRefreshToken())) {
//            throw new CustomException("Refresh Token doesn't match.", HttpStatus.BAD_REQUEST);
//        }

        log.info("redis rt {}", refreshToken2);

        return refreshTokenCookie;
    }


    public Cookie deleteCookie(Cookie[] cookies) {
        String refreshToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                log.info(String.valueOf(cookie.getName()));
                if (cookie.getName().equals("refresh-token")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        Cookie refreshTokenCookie = new Cookie("refresh-token", null);
        refreshTokenCookie.setMaxAge(0);
        refreshTokenCookie.setPath("/api/token/tokenReissue");

        return refreshTokenCookie;
    }
}
