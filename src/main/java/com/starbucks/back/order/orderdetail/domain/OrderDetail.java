package com.starbucks.back.order.orderdetail.domain;

import com.starbucks.back.order.orderlist.domain.OrderList;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "order_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_detail_uuid", nullable = false, length = 50)
    private String orderDetailUuid;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "thumbnail", nullable = false, length = 255)
    private String thumbnail;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "discount_rate", nullable = false)
    private Integer discountRate;

    @Column(name = "discount_price", nullable = false)
    private Integer discountPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_list_id", nullable = false)
    private OrderList orderList;

    @Builder
    public OrderDetail(
            Long id,
            String orderDetailUuid,
            String name,
            String thumbnail,
            Integer price,
            Integer discountRate,
            Integer discountPrice,
            Integer quantity,
            OrderList orderList
    ) {
        this.id = id;
        this.orderDetailUuid = orderDetailUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
        this.orderList = orderList;
    }
}
