package com.starbucks.back.wishlist.dto.in;

import com.starbucks.back.wishlist.domain.Wishlist;
import com.starbucks.back.wishlist.vo.in.RequestToggleWishlistVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestToggleWishlistDto {
    public String userUuid;
    private String productUuid;

    @Builder
    public RequestToggleWishlistDto(String userUuid, String productUuid) {
        this.userUuid = userUuid;
        this.productUuid = productUuid;
    }

    public Wishlist toEntity() {
        return Wishlist.builder()
                .wishlistUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .productUuid(productUuid)
                .build();
    }

    public static RequestToggleWishlistDto from(
            String userUuid,
            RequestToggleWishlistVo requestToggleWishlistVo
    ) {
        return RequestToggleWishlistDto.builder()
                .userUuid(userUuid)
                .productUuid(requestToggleWishlistVo.getProductUuid())
                .build();
    }
}
