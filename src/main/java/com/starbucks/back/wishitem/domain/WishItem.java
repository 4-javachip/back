package com.starbucks.back.wishitem.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "wish_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WishItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wish_item_uuid", nullable = false, length = 50)
    private String wishItemUuid;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "product_uuid", nullable = false, length = 50)
    private String productUuid;

    @Builder
    public WishItem(
            Long id,
            String wishItemUuid,
            String userUuid,
            String productUuid
    ) {
        this.id = id;
        this.wishItemUuid = wishItemUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
    }
}
