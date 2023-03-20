package com.soundbridge.domain.member.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ModifyProfileReq {

    @NotNull(message = "선택된 사용자가 없습니다.")
    private Long memberId;
    @NotNull(message = "프로필 이미지가 없습니다.")
    private MultipartFile profile;
}
