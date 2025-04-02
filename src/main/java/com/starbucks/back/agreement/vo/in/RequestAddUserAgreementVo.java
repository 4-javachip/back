package com.starbucks.back.agreement.vo.in;

import com.starbucks.back.agreement.domain.Agreement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RequestAddUserAgreementVo {
    @NotNull
    private Long agreementId;

    @NotNull
    private Boolean agreed;
}
