package com.starbucks.back.shippingaddress.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_shipping_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserShippingAddress extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", nullable = false, length = 50)
    private String userUuid;

    @Column(name = "shipping_address_uuid", nullable = false, length = 100)
    private String shippingAddressUuid;

    @Column(name = "defaulted", nullable = false)
    private Boolean defaulted;

    @Builder
    public UserShippingAddress(
            Long id,
            String userUuid,
            String shippingAddressUuid,
            Boolean defaulted
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.shippingAddressUuid = shippingAddressUuid;
        this.defaulted = defaulted;
    }
}
