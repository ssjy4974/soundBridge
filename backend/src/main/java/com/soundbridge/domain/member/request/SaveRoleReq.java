package com.soundbridge.domain.member.request;

import com.soundbridge.domain.member.entity.Role;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SaveRoleReq {
    @NotBlank(message = "역할이 정해지지 않았습니다.")
    private Role role;
}
