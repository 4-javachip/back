package com.starbucks.back.agreement.infrastructure;

import com.starbucks.back.agreement.domain.UserAgreement;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long> {
    List<UserAgreement> findByUserUuid(String userUuid);

    Optional<UserAgreement> findByUserAgreementUuid(String userAgreementUuid);

    Optional<UserAgreement> findByUserUuidAndAgreementId(String userUuid, Long agreementId);

    List<UserAgreement> findByUserUuidAndAgreement_Type(String userUuid, AgreementType shippingAddress);

}
