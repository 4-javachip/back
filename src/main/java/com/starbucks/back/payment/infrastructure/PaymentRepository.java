package com.starbucks.back.payment.infrastructure;

import com.starbucks.back.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * 결제 정보 생성
     * @param paymentUuid
     * @return
     */
    Boolean existsByPaymentUuid(String paymentUuid);
}
