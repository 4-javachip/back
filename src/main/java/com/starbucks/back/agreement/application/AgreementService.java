package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.dto.out.ResponseGetAgreementDto;

import java.util.List;

public interface AgreementService {
    ResponseGetAgreementDto getAgreementById(Long id);
    Agreement getAgreement(Long id);
    List<ResponseGetAgreementDto> getSignUpAgreements();
    List<ResponseGetAgreementDto> getShippingAddressAgreements();
}
