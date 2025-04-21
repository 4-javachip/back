package com.starbucks.back.wishlist.dto.out;

import com.starbucks.back.wishlist.vo.out.ResponseReadWishlistProductVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadWishlistProductDto {
//    private String wishlistUuid;
//    private String productUuid;
    private Boolean checked;

    @Builder
    public ResponseReadWishlistProductDto(
//            String wishlistUuid,
//            String productUuid,
            Boolean checked
    ) {
//        this.wishlistUuid = wishlistUuid;
//        this.productUuid = productUuid;
        this.checked = checked;
    }

    public static ResponseReadWishlistProductDto from(Boolean checked) {
        return ResponseReadWishlistProductDto.builder()
                .checked(checked)
                .build();
    }

    public ResponseReadWishlistProductVo toVo() {
        return ResponseReadWishlistProductVo.builder()
//                .wishlistUuid(wishlistUuid)
//                .productUuid(productUuid)
                .checked(checked)
                .build();
    }
}
