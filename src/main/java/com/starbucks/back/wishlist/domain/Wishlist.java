package com.starbucks.back.wishlist.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "wishlist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wishlist_uuid", nullable = false, length = 50)
    private String wishlistUuid;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "product_uuid", nullable = false, length = 50)
    private String productUuid;

    @Column(name = "product_option_list_uuid", length = 50)
    private String productOptionUuid;

    @Builder
    public Wishlist(
            Long id,
            String wishlistUuid,
            String userUuid,
            String productUuid,
            String productOptionUuid
    ) {
        this.id = id;
        this.wishlistUuid = wishlistUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.productOptionUuid = productOptionUuid;
    }
}
