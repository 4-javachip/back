package com.starbucks.back.wishitem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "wish_item")
@NoArgsConstructor
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
}
