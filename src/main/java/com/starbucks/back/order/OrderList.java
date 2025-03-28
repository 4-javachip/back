package com.starbucks.back.order;

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
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_address_uuid", nullable = false, length = 50)
    private String shippingAddressUuid;

    @Column(name = "order_code", nullable = false, length = 50)
    private String orderCode;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @OneToMany(mappedBy = "orderList", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList;

    @Builder
    public OrderList(
            Long id,
            String shippingAddressUuid,
            String orderCode,
            String userUuid,
            List<OrderDetail> orderDetailList
    ) {
        this.id = id;
        this.shippingAddressUuid = shippingAddressUuid;
        this.orderCode = orderCode;
        this.userUuid = userUuid;
        this.orderDetailList = orderDetailList;
    }
}
