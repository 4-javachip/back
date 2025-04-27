package com.starbucks.back.wishlist.dto.out;

import com.starbucks.back.wishlist.domain.Wishlist;
import com.starbucks.back.wishlist.vo.out.ResponseReadWishlistListVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReadWishlistListDto {

    private final String productUuid;

    @Builder
    public ResponseReadWishlistListDto(String productUuid) {
        this.productUuid = productUuid;
    }

    // entity => dto
    public static ResponseReadWishlistListDto from(Wishlist wishlist) {
        return ResponseReadWishlistListDto.builder()
                .productUuid(wishlist.getProductUuid())
                .build();
    }

    // dto => vo
    public ResponseReadWishlistListVo toVo() {
        return ResponseReadWishlistListVo.builder()
                .productUuid(productUuid)
                .build();
    }

}
