package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.domain.enums.PaymentStatus;
import com.starbucks.back.order.dto.in.RequestAddOrderListDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    /**
     * 주문 내역 리스트 조회 by userUuid
     */
    List<OrderList> findAllByUserUuid(String userUuid);

    /**
     * 주문 내역 조회 by orderListUuid
     */
    Optional<OrderList> findByOrderListUuid(String orderListUuid);

    /**
     * 최근 주문 내역 조회 by userUuid
     */
    Optional<OrderList> findTopByUserUuidOrderByCreatedAtDesc(String userUuid);

    /**
     * 주문 내역 수정 by orderListUuid
     */
    @Modifying
    @Transactional
    @Query("UPDATE OrderList o SET o.paymentStatus = :paymentStatus WHERE o.orderListUuid = :orderListUuid")
    void updateOrderListStatus(
            @Param("orderListUuid") String orderListUuid,
            @Param("paymentStatus") PaymentStatus paymentStatus);

    /**
     * 주문 내역 존재 여부 조회 by userUuid, productUuid
     * 결제 완료 상태면 paymentStatus = 3
     */
    @Query("""
        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
        FROM OrderDetail od
        JOIN OrderList ol ON od.orderListUuid = ol.orderListUuid
        WHERE od.productUuid = :productUuid
          AND ol.userUuid = :userUuid
          AND ol.paymentStatus = 3
    """)
    Boolean existsOrderByUserUuidAndProductUuid(
            @Param("userUuid") String userUuid,
            @Param("productUuid") String productUuid
    );
}
