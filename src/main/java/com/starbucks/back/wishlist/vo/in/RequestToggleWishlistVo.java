package com.starbucks.back.wishlist.vo.in;

import lombok.Getter;

@Getter
public class RequestToggleWishlistVo {
    private String productUuid;
    private String productOptionListUuid;
}
