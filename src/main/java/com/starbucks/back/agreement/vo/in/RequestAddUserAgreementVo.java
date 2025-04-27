package com.starbucks.back.agreement.vo.in;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RequestAddUserAgreementVo {
    @NotNull
    private Long agreementId;

    private Boolean agreed;
}
