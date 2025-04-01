package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;

public interface UserAgreementService {

    void addUserAgreement(RequestAddUserAgreementDto requestAddUserAgreementDto);
}
