package com.starbucks.back.coupon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.SoftDeletableEntity;
import com.starbucks.back.coupon.domain.enums.CouponState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_registry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponRegistry extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "user_uuid", length = 50, nullable = false)
    private String userUuid;

    @Column(name = "coupon_uuid", length = 50, nullable = false)
    private String couponUuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CouponState state;

    @Builder
    public CouponRegistry(Long id, String userUuid, String couponUuid, CouponState state) {
        this.id = id;
        this.userUuid = userUuid;
        this.couponUuid = couponUuid;
        this.state = state;
    }

}
