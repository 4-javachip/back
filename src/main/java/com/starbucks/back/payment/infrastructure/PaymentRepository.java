package com.starbucks.back.payment.infrastructure;

import com.starbucks.back.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * 결제 UUID 여부 파악
     * @param paymentUuid
     * @return
     */
    Boolean existsByPaymentUuid(String paymentUuid);

    /**
     * 결제 조회 by paymentUuid
     * @param
     * @return
     */
    Optional<Payment> findByPaymentUuid(String paymentUuid);
}
