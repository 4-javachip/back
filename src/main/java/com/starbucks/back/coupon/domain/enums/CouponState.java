package com.starbucks.back.coupon.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponState {
    UNUSED("사용 안 함"),
    USED("사용함"),
    EXPIRED("만료됨");

    @JsonValue
    private final String label;
}