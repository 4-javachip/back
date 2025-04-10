package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.domain.UserAgreement;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;
import com.starbucks.back.agreement.dto.out.ResponseGetUserAgreementDto;
import com.starbucks.back.agreement.infrastructure.UserAgreementRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserAgreementServiceImpl implements UserAgreementService {

    private final AgreementService agreementService;
    private final UserAgreementRepository userAgreementRepository;
    private final UserShippingAddressService userShippingAddressService;


    @Override
    @Transactional
    public void addUserAgreement(RequestAddUserAgreementDto requestAddUserAgreementDto) {
        final Agreement agreement = agreementService.getAgreement(requestAddUserAgreementDto.getAgreementId());

        userAgreementRepository.findByUserUuidAndAgreementId(
                    requestAddUserAgreementDto.getUserUuid(), requestAddUserAgreementDto.getAgreementId()
                )
                .ifPresentOrElse(
                        userAgreement -> {
                            final Boolean beforeAgreed = userAgreement.getAgreed();
                            final Boolean afterAgreed = requestAddUserAgreementDto.getAgreed();

                            userAgreement.updateAgreed(afterAgreed);

                            // 동의 → 비동의 전환 시 배송지 전체 삭제
                            if (beforeAgreed && !afterAgreed) {
                                userShippingAddressService.deleteAllShippingAddressByUserUuid(requestAddUserAgreementDto.getUserUuid());
                            }
                        },
                        () -> userAgreementRepository.save(requestAddUserAgreementDto.toEntity(agreement))
                );
    }

    @Override
    public List<ResponseGetUserAgreementDto> getUserAgreementsByUserUuid(String userUuid) {
        return userAgreementRepository.findByUserUuid(userUuid)
                .stream()
                .map(ResponseGetUserAgreementDto::from)
                .toList();
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

    @Override
    public List<ResponseGetUserAgreementDto> getUserShippingAddressAgreementByUserUuid(String userUuid) {
        final List<UserAgreement> agreements = userAgreementRepository.findByUserUuidAndAgreement_Type(userUuid, AgreementType.SHIPPING_ADDRESS);

        if (agreements.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_USER_SHIPPING_ADDRESS_AGREEMENT);
        }

        return agreements.stream()
                .map(ResponseGetUserAgreementDto::from)
                .toList();
    }

    @Override
    public List<ResponseGetUserAgreementDto> getUserSignUpAgreementByUserUuid(String userUuid) {
        final List<UserAgreement> agreements = userAgreementRepository.findByUserUuidAndAgreement_Type(userUuid, AgreementType.SIGN_UP);

        if (agreements.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_USER_SIGN_UP_AGREEMENT);
        }

        return agreements.stream()
                .map(ResponseGetUserAgreementDto::from)
                .toList();
    }
}