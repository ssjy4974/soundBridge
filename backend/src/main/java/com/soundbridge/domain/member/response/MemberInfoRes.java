package com.soundbridge.domain.member.response;

import com.soundbridge.domain.member.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoRes {

    private Long memberId;
    private String email;
    private String nickname;
    private String profile;
    private Role role;

    @Builder
    public MemberInfoRes(Long memberId, String email, String nickname, String profile,
        Role role) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
        this.role = role;
    }

    public static MemberInfoRes of(Long memberId, String email, String nickname, String profile,
        Role role) {
        return MemberInfoRes.builder()
            .memberId(memberId)
            .email(email)
            .nickname(nickname)
            .profile(profile)
            .role(role)
            .build();

    }
}
