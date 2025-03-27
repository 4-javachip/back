package com.starbucks.back.coupon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon_registry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 유저 uuid
     */
    @Column(name = "user_uuid", length = 50, nullable = false)
    private String userUuid;

    /**
     * 쿠폰 uuid
     */
    @Column(name = "coupon_uuid", length = 50, nullable = false)
    private String couponUuid;

    /**
     * 쿠폰 사용 여부
     */
    @Column(name = "state", nullable = false)
    private Boolean state;

    @Builder
    public CouponRegistry(Long id, String userUuid, String couponUuid, Boolean state) {
        this.id = id;
        this.userUuid = userUuid;
        this.couponUuid = couponUuid;
        this.state = state;
    }

}
