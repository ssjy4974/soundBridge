package com.soundbridge.domain.member.oauth;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.entity.Role;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.service.TokenService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final MemberRepository memberRepository;
    @Value("${LOGIN_SUCCESS_URL}")
    private String loginSuccessUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("attributes {}", attributes);

        Member member = memberRepository.findByEmail((String) attributes.get("email"));

        if (member == null) { //redirect
            member = Member.builder()
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("nickname"))
                .gender((String) attributes.get("gender"))
                .profile((String) attributes.get("profile"))
                .age((int) attributes.get("age"))
                .build();

            memberRepository.save(member);
        }

        Token token = tokenService.generateToken(member.getId(), "USER");
        log.info("JwT : {}", token);

        Cookie cookie = new Cookie("refresh-token", token.getRefreshToken());
        // expires in 7 days
        cookie.setMaxAge(60 * 60 * 24* 7);

        // optional properties
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/token/tokenReissue");

        // add cookie to response
        response.addCookie(cookie);

        response.sendRedirect(loginSuccessUrl+token.getToken());
    }
}