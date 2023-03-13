package com.soundbridge.domain.member.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ModifyNicknameReq {
    @NotBlank(message = "닉네임이 공백입니다.")
    private String nickname;
}
