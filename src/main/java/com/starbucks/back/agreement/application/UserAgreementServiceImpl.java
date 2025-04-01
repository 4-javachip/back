package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;
import com.starbucks.back.agreement.dto.out.ResponseGetUserAgreementDto;
import com.starbucks.back.agreement.infrastructure.AgreementRepository;
import com.starbucks.back.agreement.infrastructure.UserAgreementRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAgreementServiceImpl implements UserAgreementService {

    private final AgreementRepository agreementRepository;
    private final UserAgreementRepository userAgreementRepository;

    @Override
    public void addUserAgreement(RequestAddUserAgreementDto requestAddUserAgreementDto) {
        userAgreementRepository.save(
                requestAddUserAgreementDto.toEntity(
                        agreementRepository.findById(requestAddUserAgreementDto.getAgreementId())
                                .orElseThrow(
                                        () -> new BaseException(BaseResponseStatus.INVALID_AGREEMENT_ID)
                                )
                )
        );
    }

    @Override
    public ResponseGetUserAgreementDto getUserAgreementByUserAgreementUuid(String userAgreementUuid) {
        return ResponseGetUserAgreementDto.from(
                userAgreementRepository.findByUserAgreementUuid(userAgreementUuid)
                        .orElseThrow(
                                () -> new BaseException(BaseResponseStatus.INVALID_USER_AGREEMENT_UUID)
                        )
        );
    }
}