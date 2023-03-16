package com.soundbridge.global.config;

import com.soundbridge.domain.member.oauth.OAuth2SuccessHandler;
import com.soundbridge.domain.member.repository.MemberRepository;
import com.soundbridge.domain.member.service.CustomOAuth2UserService;
import com.soundbridge.domain.member.service.TokenService;
import com.soundbridge.global.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .httpBasic().disable()      // Http basic Auth  기반으로 로그인 인증창이 뜸.  disable 시에 인증창 뜨지 않음.
            .csrf().disable()       // rest api이므로 csrf 보안이 필요없으므로 disable처리.
            .formLogin().disable() // 로그인 폼 미사용
            .httpBasic().disable() // Http basic Auth 기반으로 열리는 로그인 인증창 미사용
            .sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)// jwt token으로 인증하므로 세션 사용하지 않음. stateless 하도록 처리.
            .and()
            .authorizeRequests()
            .antMatchers("/api/token/**", "/oauth2/**").permitAll() //토큰 재발급 요청은 제외

            .anyRequest().authenticated() // 그외의 모든 요청은 인증 필요.
            .and()// 인증권한이 필요한 페이지.// 나머지 모든 요청 허용  ( 생략 가능 )
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint().userService(customOAuth2UserService);
//            .failureHandler(oAuth2AuthenticationFailureHandler);
//
        http.addFilterAfter(new JwtAuthFilter(tokenService, memberRepository),
            UsernamePasswordAuthenticationFilter.class);
//
//        http.exceptionHandling()
//            .accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }

//     필터 무시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/static/**", "/js/**", "/webjars/**");
    }

    // Used by Spring Security if CORS is enabled.
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source =
//            new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("http://localhost");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

}