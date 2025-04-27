package com.starbucks.back.recentviewed.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RequestAddRecentViewedProductVo {
    @NotBlank(message = "productUuid는 필수입니다.")
    private String productUuid;
}
