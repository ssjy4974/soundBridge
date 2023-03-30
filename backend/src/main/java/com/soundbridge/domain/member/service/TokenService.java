package com.soundbridge.domain.member.service;

import com.soundbridge.domain.member.oauth.Token;
import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {

    private final Environment env;
    private final RedisTemplate redisTemplate;
    private final MemberService memberService;
    private String secretKey;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder()
            .encodeToString(env.getProperty("jwt.token.secret-key").getBytes());
    }

    public Token generateToken(Long uid, String role) {
        long tokenPeriod = Long.parseLong(
            env.getProperty("jwt.access-token.expire-length")); // 10 min
        long refreshPeriod = Long.parseLong(
            env.getProperty("jwt.refresh-token.expire-length")); // 2 week

        Claims claims = Jwts.claims().setSubject(uid.toString());
        claims.put("role", role);

        Date now = new Date();
        return new Token(
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact(),
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact());
    }

    public Token generateToken(Long uid, String role, String refreshToken) {
        long tokenPeriod = Long.parseLong(
            env.getProperty("jwt.access-token.expire-length")); // 15 min

        Claims claims = Jwts.claims().setSubject(uid.toString());
        claims.put("role", role);

        Date now = new Date();
        return new Token(
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact(),
            refreshToken);
    }

    public boolean verifyToken(String token, String email, HttpServletResponse response)
        throws IOException {
        try {
            Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
            log.info("claims {}", Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token));
            log.info("token verify {}", redisTemplate.opsForValue().get(email));
            ValueOperations<String, String> logoutValueOperations = redisTemplate.opsForValue();

            if (logoutValueOperations.get(email) == null) {
                log.info("refresh token 없음. -> 재 로그인");
                return false;
            }
            return claims.getBody()
                .getExpiration()
                .after(new Date());
        } catch (ExpiredJwtException e) {
            ((HttpServletResponse) response).sendError(ErrorCode.NOT_AUTHENTICATION.getCode(), ErrorCode.NOT_AUTHENTICATION.getMessage());
        } catch (SignatureException | MalformedJwtException e) {
            ((HttpServletResponse) response).sendError(401, "SignatureException error");
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        return true;
    }

    public Long getExpiration(String token) {
        try {
            log.info("expiration {}",
                Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody());
            Date expiration = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
                .getExpiration();
            Long now = new Date().getTime();

            return expiration.getTime() - now;
        } catch (ExpiredJwtException e) {
            return -1L;
        }
    }

    public Long getUid(String token) {
        return Long.valueOf(
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }

    public String getRole(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
            .get("role");
    }

    public String reGenerateAccessToken(Cookie[] cookies, HttpServletResponse response)
        throws IOException {
        String refreshToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh-token")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if(refreshToken == null){
            throw new AccessDeniedException(ErrorCode.NOT_AUTHENTICATION);
        }

        Long id = getUid(refreshToken);
        String email = memberService.getMemberById(id).getEmail();

        if (refreshToken.equals("") || !verifyToken(refreshToken, email, response)) {
            throw new AccessDeniedException(ErrorCode.NOT_AUTHENTICATION);
        }

        log.info("refreshToken {}, role {}", refreshToken, getRole(refreshToken));

        String role = getRole(refreshToken);

        return generateToken(id, role, refreshToken).getToken();
    }
}