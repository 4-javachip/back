package com.starbucks.back.order.domain;

import com.starbucks.back.common.entity.BaseEntity;
import com.starbucks.back.order.domain.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "order_list")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_list_uuid", nullable = false, length = 50)
    private String orderListUuid;

    @Column(name = "payment_uuid", nullable = false, length = 50)
    private String paymentUuid;

    @Column(name = "order_code", nullable = false, length = 50)
    private String orderCode;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(name = "payment_status", nullable = false, length = 50)
    private PaymentStatus paymentStatus;

    @Column(name = "total_order_price", nullable = false)
    private Integer totalOrderPrice;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "shipping_address_uuid", nullable = false, length = 50)
    private String shippingAddressUuid;

    @Builder
    public OrderList(
            Long id,
            String orderListUuid,
            String paymentUuid,
            String orderCode,
            String userUuid,
            Integer discountRate,
            PaymentStatus paymentStatus,
            Integer totalOrderPrice,
            Integer totalAmount,
            String shippingAddressUuid
    ) {
        this.id = id;
        this.orderListUuid = orderListUuid;
        this.paymentUuid = paymentUuid;
        this.orderCode = orderCode;
        this.userUuid = userUuid;
        this.discountRate = discountRate;
        this.paymentStatus = paymentStatus;
        this.totalOrderPrice = totalOrderPrice;
        this.totalAmount = totalAmount;
        this.shippingAddressUuid = shippingAddressUuid;
    }
}
