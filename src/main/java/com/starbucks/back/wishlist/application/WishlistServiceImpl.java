package com.starbucks.back.wishlist.application;

import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;
import com.starbucks.back.wishlist.infrastructure.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepository wishlistRepository;

    /**
     * 찜 List 조회 (userUuid)
     */
    public List<ResponseReadWishlistListDto> getWishlistListByUserUuid(String userUuid) {
        return wishlistRepository.findAllByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseReadWishlistListDto::from)
                .toList();
    }
}
