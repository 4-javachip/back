package com.starbucks.back.wishlist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadWishlistListVo {

    private String wishlistUuid;
    private String productUuid;

    @Builder
    public ResponseReadWishlistListVo(String wishlistUuid, String userUuid, String productUuid) {
        this.wishlistUuid = wishlistUuid;
        this.productUuid = productUuid;
    }
}
