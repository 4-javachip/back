package com.starbucks.back.order.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "order_detail")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_list_uuid", nullable = false, length = 50)
    private String orderListUuid;

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

    @Builder
    public OrderDetail(
            Long id,
            String orderListUuid,
            String orderDetailUuid,
            String name,
            String thumbnail,
            Integer price,
            Integer discountRate,
            Integer discountPrice,
            Integer quantity
    ) {
        this.id = id;
        this.orderListUuid = orderListUuid;
        this.orderDetailUuid = orderDetailUuid;
        this.name = name;
        this.thumbnail = thumbnail;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
    }

}
