package com.starbucks.back.agreement.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestGetUserAgreementVo {
    @NotBlank
    private String userAgreementUuid;
}
