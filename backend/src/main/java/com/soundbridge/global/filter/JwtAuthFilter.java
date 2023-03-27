package com.soundbridge.global.filter;

import com.soundbridge.domain.member.entity.Member;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.response.MemberAccessRes;
import com.soundbridge.domain.member.service.TokenService;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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

        String accessTokenHeader = request.getHeader("access-token");
        if (accessTokenHeader == null || !accessTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("accessToken {} ", accessTokenHeader);

        final String accessToken = accessTokenHeader.split(" ")[1].trim();
        if (!tokenService.verifyToken(accessToken)) { //만료 되면 멤버 정보 없이 리턴
            filterChain.doFilter(request, response);
            return;
        }

        Long id = tokenService.getUid(accessToken);
        Member member = memberRepository.getReferenceById(id);

        MemberAccessRes memberAccessRes = new MemberAccessRes(member.getId(), accessToken);

        Authentication auth = getAuthentication(memberAccessRes);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);

    }

    public Authentication getAuthentication(MemberAccessRes member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}