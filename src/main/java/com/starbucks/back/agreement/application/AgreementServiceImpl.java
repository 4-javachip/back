package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.agreement.dto.out.ResponseGetAgreementDto;
import com.starbucks.back.agreement.infrastructure.AgreementRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    @Override
    public ResponseGetAgreementDto getAgreementById(Long id) {
        return ResponseGetAgreementDto.from(
                agreementRepository.findById(id)
                        .orElseThrow(
                                () -> new BaseException(BaseResponseStatus.INVALID_AGREEMENT_ID)
                        )
        );
    }

    @Override
    public Agreement getAgreement(Long id) {
        return agreementRepository.findById(id)
                .orElseThrow(
                        () -> new BaseException(BaseResponseStatus.INVALID_AGREEMENT_ID)
                );
    }

    @Override
    public List<ResponseGetAgreementDto> getSignUpAgreements() {
        return agreementRepository.findByType(AgreementType.SIGN_UP)
                .stream()
                .map(ResponseGetAgreementDto::from)
                .toList();
    }

    @Override
    public List<ResponseGetAgreementDto> getShippingAddressAgreements() {
        return agreementRepository.findByType(AgreementType.SHIPPING_ADDRESS)
                .stream()
                .map(ResponseGetAgreementDto::from)
                .toList();
    }

}
