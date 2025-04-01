package com.starbucks.back.agreement.vo.in;

import com.starbucks.back.agreement.domain.Agreement;
import lombok.Getter;

@Getter
public class RequestAddUserAgreementVo {
    private Long agreementId;
    private Boolean agreed;
    private String userUuid;
}
