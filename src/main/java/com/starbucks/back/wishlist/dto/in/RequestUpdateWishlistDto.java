package com.starbucks.back.wishlist.dto.in;

import com.starbucks.back.wishlist.domain.Wishlist;
import com.starbucks.back.wishlist.vo.in.RequestUpdateWishlistVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RequestUpdateWishlistDto {
    private String userUuid;
    private String productUuid;
    private String productOptionListUuid;

    @Builder
    public RequestUpdateWishlistDto(String userUuid, String productUuid, String productOptionListUuid) {
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.productOptionListUuid = productOptionListUuid;
    }

    public Wishlist updateWishlist(Wishlist wishlist) {
        return Wishlist.builder()
                .id(wishlist.getId())
                .wishlistUuid(wishlist.getWishlistUuid())
                .userUuid(userUuid)
                .productUuid(productUuid)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }

    public Wishlist toEntity() {
        return Wishlist.builder()
                .wishlistUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .productUuid(productUuid)
                .productOptionListUuid(productOptionListUuid)
                .build();
    }

    public static RequestUpdateWishlistDto from(RequestUpdateWishlistVo requestUpdateWishlistVo) {
        return RequestUpdateWishlistDto.builder()
                .userUuid(requestUpdateWishlistVo.getUserUuid())
                .productUuid(requestUpdateWishlistVo.getProductUuid())
                .productOptionListUuid(requestUpdateWishlistVo.getProductOptionListUuid())
                .build();
    }
}
