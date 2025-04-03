package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;
import com.starbucks.back.agreement.dto.out.ResponseGetUserAgreementDto;

import java.util.List;

public interface UserAgreementService {

    void addUserAgreement(RequestAddUserAgreementDto requestAddUserAgreementDto);

    List<ResponseGetUserAgreementDto> getUserAgreementsByUserUuid(String userUuid);
    ResponseGetUserAgreementDto getUserAgreementByUserAgreementUuid(String userAgreementUuid);

    List<ResponseGetUserAgreementDto> getUserShippingAddressAgreementByUserUuid(String userUuid);

    List<ResponseGetUserAgreementDto> getUserSignUpAgreementByUserUuid(String userUuid);
}
