package com.starbucks.back.user.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestWithdrawalUserVo {
    @NotBlank(message = "탈퇴 사유를 입력해주세요.")
    private String reason;
}
