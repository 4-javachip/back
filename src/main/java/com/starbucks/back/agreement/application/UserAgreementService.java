package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;
import com.starbucks.back.agreement.dto.out.ResponseGetUserAgreementDto;

public interface UserAgreementService {

    void addUserAgreement(RequestAddUserAgreementDto requestAddUserAgreementDto);

    ResponseGetUserAgreementDto getUserAgreementByUserAgreementUuid(String userAgreementUuid);
}
