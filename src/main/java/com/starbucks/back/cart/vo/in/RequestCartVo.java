package com.starbucks.back.cart.vo.in;

import lombok.Getter;

@Getter
public class RequestCartVo {

    private String userUuid;
    private String cartUuid;
    private Integer productQuantity;
    private Boolean checked;
    private String productUuid;
    private String productOptionListUuid;
}
