package com.starbucks.back.coupon.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestAddCouponVo {
    @NotBlank(message = "쿠폰 이름은 필수입니다.")
    private String name;
    @NotBlank(message = "할인 타입은 필수입니다. ")
    private String discountType;
    @NotNull(message = "할인 금액은 필수입니다.")
    private Integer discountAmount;
    @NotNull(message = "최대 할인 금액은 필수입니다.")
    private Integer maxDiscountAmount;
    @NotNull(message = "유효 시작일은 필수입니다.")
    private LocalDate validFrom;
    @NotNull(message = "유효 종료일은 필수입니다.")
    private LocalDate validUntil;
    private String supply;

}
