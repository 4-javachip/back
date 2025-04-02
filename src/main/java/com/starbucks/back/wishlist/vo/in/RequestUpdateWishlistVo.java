package com.starbucks.back.wishlist.vo.in;

import lombok.Getter;

@Getter
public class RequestUpdateWishlistVo {
    private String userUuid;
    private String productUuid;
    private String productOptionListUuid;
}
