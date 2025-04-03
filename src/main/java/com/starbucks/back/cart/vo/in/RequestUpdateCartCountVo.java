package com.starbucks.back.cart.vo.in;

import lombok.Getter;

@Getter
public class RequestUpdateCartCountVo {
    private String cartUuid;
    private Integer productQuantity;
}
