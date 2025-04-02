package com.starbucks.back.wishlist.dto.in;

import com.starbucks.back.wishlist.domain.Wishlist;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class RequestReadWishlistDto {

    private String userUuid;

    @Builder
    public RequestReadWishlistDto(String userUuid) {
        this.userUuid = userUuid;
    }

    // vo => dto
    public static RequestReadWishlistDto from(String userUuid) {
        return RequestReadWishlistDto.builder()
                .userUuid(userUuid)
                .build();
    }
}
