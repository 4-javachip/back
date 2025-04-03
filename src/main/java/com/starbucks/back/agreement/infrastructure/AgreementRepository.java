package com.starbucks.back.agreement.infrastructure;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    List<Agreement> findByType(AgreementType type);
    Optional<Agreement> findById(Long id);
}
