package com.starbucks.back.cart.vo.in;

import lombok.Getter;

@Getter
public class RequestUpdateCartCheckedVo {
    public String cartUuid;
    public Boolean checked;
}
