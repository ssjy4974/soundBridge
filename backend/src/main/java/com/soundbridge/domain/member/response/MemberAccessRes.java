package com.soundbridge.domain.member.response;

import lombok.Getter;

@Getter
public class MemberAccessRes {
    private Long id;

    private String accessToken;

    public MemberAccessRes(Long id, String accessToken) {
        this.id = id;
        this.accessToken = accessToken;
    }
}