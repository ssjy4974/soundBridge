package com.soundbridge.domain.member.request;

import com.soundbridge.domain.member.entity.Role;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SaveAddInfoReq {

    @NotNull(message = "선택된 사용자가 없습니다.")
    private Long memberId;
    @NotBlank(message = "역할이 정해지지 않았습니다.")
    private Role role;
    @NotBlank(message = "연령대가 입력되지 않았습니다.")
    private int age;
    @NotBlank(message = "성별이 정해지지 않았습니다.")
    private String gender;
}
