package com.starbucks.back.wishlist.dto.in;

import com.starbucks.back.wishlist.domain.Wishlist;
import com.starbucks.back.wishlist.vo.in.RequestToggleWishlistVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestToggleWishlistDto {
    private String userUuid;
    private String productUuid;
    private String productOptionListUuid;

    @Builder
    public RequestToggleWishlistDto(String userUuid, String productUuid, String productOptionListUuid) {
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.productOptionListUuid = productOptionListUuid;
    }

    public Wishlist toEntity() {
        return Wishlist.builder()
                .wishlistUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .productUuid(productUuid)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }

    public static RequestToggleWishlistDto from(
            String userUuid,
            RequestToggleWishlistVo requestToggleWishlistVo
    ) {
        return RequestToggleWishlistDto.builder()
                .userUuid(userUuid)
                .productUuid(requestToggleWishlistVo.getProductUuid())
                .productOptionListUuid(requestToggleWishlistVo.getProductOptionListUuid())
                .build();
    }
}
