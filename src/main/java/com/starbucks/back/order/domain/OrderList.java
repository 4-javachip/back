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

    @Column(name = "order_code", nullable = false, length = 50, unique = true)
    private String orderCode;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "discount_rate")
    private Integer discountRate;

    @Column(name = "payment_status", nullable = false, length = 50)
    private PaymentStatus paymentStatus;

    @Column(name = "total_origin_price", nullable = false)
    private Integer totalOriginPrice;

    @Column(name = "total_purchase_price", nullable = false)
    private Integer totalPurchasePrice;

    @Column(name = "address_name", length = 50)
    private String addressName;

    @Column(name = "recipient_name", length = 50)
    private String recipientName;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "base_address", length = 50)
    private String baseAddress;

    @Column(name = "detail_address", length = 50)
    private String detailAddress;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "second_phone_number", length = 20)
    private String secondPhoneNumber;

    @Column(name = "shipping_note", length = 1000)
    private String shippingNote;

    @Builder
    public OrderList(
            String orderListUuid,
            String paymentUuid,
            String orderCode,
            String userUuid,
            Integer discountRate,
            PaymentStatus paymentStatus,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote
    ) {
        this.orderListUuid = orderListUuid;
        this.paymentUuid = paymentUuid;
        this.orderCode = orderCode;
        this.userUuid = userUuid;
        this.discountRate = discountRate;
        this.paymentStatus = paymentStatus;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.addressName = addressName;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.shippingNote = shippingNote;
    }
}
