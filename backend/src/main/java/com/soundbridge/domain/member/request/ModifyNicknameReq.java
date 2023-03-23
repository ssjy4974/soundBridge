package com.soundbridge.domain.member.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifyNicknameReq {
    @NotNull(message = "선택된 사용자가 없습니다.")
    private Long memberId;
    @NotBlank(message = "닉네임이 공백입니다.")
    private String nickname;
}
