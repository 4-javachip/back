package com.starbucks.back.agreement.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseGetUserAgreementVo {
    private Long agreementId;
    private Boolean agreed;
    private String userUuid;
    private String userAgreementUuid;

    @Builder
    public ResponseGetUserAgreementVo(Long agreementId, Boolean agreed, String userUuid, String userAgreementUuid) {
        this.agreementId = agreementId;
        this.agreed = agreed;
        this.userUuid = userUuid;
        this.userAgreementUuid = userAgreementUuid;
    }
}
