package com.starbucks.back.agreement.dto.in;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.domain.UserAgreement;
import com.starbucks.back.agreement.vo.in.RequestAddUserAgreementVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
public class RequestAddUserAgreementDto {
    private Long agreementId;
    private Boolean agreed;
    private String userUuid;

    @Builder
    public RequestAddUserAgreementDto(Long agreementId, Boolean agreed, String userUuid) {
        this.agreementId = agreementId;
        this.agreed = agreed;
        this.userUuid = userUuid;
    }

    public UserAgreement toEntity(Agreement agreement) {
        return UserAgreement.builder()
                .agreement(agreement)
                .agreed(agreed)
                .userUuid(userUuid)
                .userAgreementUuid(randomUUID().toString())
                .build();
    }
    public static RequestAddUserAgreementDto of(String userUuid, RequestAddUserAgreementVo requestAddUserAgreementVo) {
        return RequestAddUserAgreementDto.builder()
                .agreementId(requestAddUserAgreementVo.getAgreementId())
                .agreed(requestAddUserAgreementVo.getAgreed())
                .userUuid(userUuid)
                .build();
    }
}
