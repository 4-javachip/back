package com.starbucks.back.wishlist.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadWishlistProductVo {
//    private String wishlistUuid;
//    private String productUuid;
    private Boolean checked;

    @Builder
    public ResponseReadWishlistProductVo(
//            String wishlistUuid,
//            String productUuid,
            Boolean checked
    ) {
//        this.wishlistUuid = wishlistUuid;
//        this.productUuid = productUuid;
        this.checked = checked;
    }
}
