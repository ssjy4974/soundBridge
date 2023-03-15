package com.soundbridge.global.filter;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.oauth.Token;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.response.MemberAccessRes;
import com.soundbridge.domain.member.service.TokenService;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import com.soundbridge.global.error.exception.NotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws IOException, ServletException {

        log.info("Filter 진입");
        log.info("요청 타입 {}", request.getMethod());
        log.info("요청 타입 uri {}", request.getRequestURI());

        String accessToken = (request).getHeader("access-token");
        String refreshToken = null;

        Cookie[] cookies = request.getCookies();
//        log.info("cokies {}", cookies); // access 재요청 주소일 경우만 cookie에서 가져오기
//
//        if(cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("refresh-token")) {
//                    logger.info(cookie.getValue());
//                    refreshToken = cookie.getValue();
//                    break;
//                }
//            }
//        }

        log.info("accessToken {} ", accessToken);

        //엑세스 토큰이 없을때
        if(accessToken == null || "".equals(accessToken)){

            if (refreshToken != null && tokenService.verifyToken(refreshToken)){

                //refresh-token을 받음 access-token 재발급
                Long id = tokenService.getUid(refreshToken);
                Token token = tokenService.reGenerateAccessToken(id, "USER", refreshToken);
                Member member = memberRepository.getReferenceById(id);

                if (member == null) {
                    throw new NotFoundException(ErrorCode.MEMBER_NOT_FOUND);
                }

                MemberAccessRes memberAccessRes = new MemberAccessRes(member.getId(), token.getToken());

                Authentication auth = getAuthentication(memberAccessRes);
                SecurityContextHolder.getContext().setAuthentication(auth);

                // 정상 진행

            } else if (request.getRequestURI().contains("api/game-room")){

                // 정상 진행

            } else if (request.getMethod().equals("GET")){

                // 정상 진행

            } else {

                log.info(String.valueOf(ErrorCode.EXPIRED_ACCESSTOKEN));
                throw new AccessDeniedException(ErrorCode.EXPIRED_ACCESSTOKEN);

            }

        } else {
            Long id = tokenService.getUid(accessToken);
            Member member = memberRepository.getReferenceById(id);

            if(tokenService.verifyToken(accessToken)){

                if (member == null) {
                    throw new NotFoundException(ErrorCode.MEMBER_NOT_FOUND);
                }

                MemberAccessRes memberAccessRes = new MemberAccessRes(member.getId(), accessToken);

                Authentication auth = getAuthentication(memberAccessRes);
                SecurityContextHolder.getContext().setAuthentication(auth);

                // 정상 진행

            } else {

                log.info(String.valueOf(ErrorCode.EXPIRED_ACCESSTOKEN));
                throw new AccessDeniedException(ErrorCode.EXPIRED_ACCESSTOKEN);

            }

        }

        filterChain.doFilter(request, response);

    }

    public Authentication getAuthentication(MemberAccessRes member) {
        return new UsernamePasswordAuthenticationToken(member, "",
            Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}