package com.starbucks.back.coupon.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {
    //TODO 쿠폰 종류 정해지면 수정
    COUPON("쿠폰"),
    GIFTICON("기프티콘"),
    ETC("기타");

    private final String couponType;

    @JsonValue
    public String getCouponType() {
        return couponType;
    }

    @JsonCreator
    public static CouponType fromString(String value) {
        for (CouponType couponType: CouponType.values()) {
            if (couponType.couponType.equals(value)) {
                return couponType;
            }
        }
        throw new IllegalArgumentException("UnKnown value: " + value);
    }


}
