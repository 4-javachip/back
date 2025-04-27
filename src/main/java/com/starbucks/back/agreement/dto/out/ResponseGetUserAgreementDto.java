package com.starbucks.back.agreement.dto.out;

import com.starbucks.back.agreement.domain.UserAgreement;
import com.starbucks.back.agreement.vo.out.ResponseGetUserAgreementVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseGetUserAgreementDto {
    private Long agreementId;
    private Boolean agreed;
    private String userUuid;
    private String userAgreementUuid;

    @Builder
    public ResponseGetUserAgreementDto(Long agreementId, Boolean agreed, String userUuid, String userAgreementUuid) {
        this.agreementId = agreementId;
        this.agreed = agreed;
        this.userUuid = userUuid;
        this.userAgreementUuid = userAgreementUuid;
    }

    public static ResponseGetUserAgreementDto from(UserAgreement userAgreement) {
        return ResponseGetUserAgreementDto.builder()
                .agreementId(userAgreement.getAgreement().getId())
                .agreed(userAgreement.getAgreed())
                .userUuid(userAgreement.getUserUuid())
                .userAgreementUuid(userAgreement.getUserAgreementUuid())
                .build();
    }

    public ResponseGetUserAgreementVo toVo() {
        return ResponseGetUserAgreementVo.builder()
                .agreementId(agreementId)
                .agreed(agreed)
                .userUuid(userUuid)
                .userAgreementUuid(userAgreementUuid)
                .build();
    }

}
