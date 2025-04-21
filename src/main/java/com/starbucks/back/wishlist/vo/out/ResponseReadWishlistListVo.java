package com.starbucks.back.wishlist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadWishlistListVo {

    private String productUuid;

    @Builder
    public ResponseReadWishlistListVo(String wishlistUuid, String userUuid, String productUuid) {
        this.productUuid = productUuid;
    }
}
