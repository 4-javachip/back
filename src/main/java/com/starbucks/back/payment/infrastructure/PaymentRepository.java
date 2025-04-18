package com.starbucks.back.payment.infrastructure;

import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
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

    /**
     * 결제 상태 수정
     * @param paymentUuid
     * @return
     */
    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.status = :status, p.approvedAt = :approvedAt WHERE p.paymentUuid = :paymentUuid")
    void updatePaymentStatus(
            @Param("paymentUuid") String paymentUuid,
            @Param("status") PaymentStatus status,
            @Param("approvedAt") LocalDateTime approvedAt
    );
}
