package com.starbucks.back.cart.vo.in;

import lombok.Getter;

@Getter
public class RequestAddCartVo {
    private String userUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productOptionListUuid;
}
