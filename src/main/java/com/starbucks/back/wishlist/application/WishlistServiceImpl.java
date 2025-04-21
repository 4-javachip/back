package com.starbucks.back.wishlist.application;

import com.starbucks.back.wishlist.domain.Wishlist;
import com.starbucks.back.wishlist.dto.in.RequestToggleWishlistDto;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistProductDto;
import com.starbucks.back.wishlist.infrastructure.WishlistRepository;
import jakarta.transaction.Transactional;
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
    @Override
    public List<ResponseReadWishlistListDto> getWishlistListByUserUuid(String userUuid) {
        return wishlistRepository.findAllByUserUuid(userUuid)
                .stream()
                .map(ResponseReadWishlistListDto::from)
                .toList();
    }

    /**
     * 찜 수정 (userUuid, productUuid, productOptionUuid)
     */
    @Transactional
    @Override
    public void toggleWishlist(RequestToggleWishlistDto requestToggleWishlistDto) {
        wishlistRepository.findByUserUuidAndProductUuid(
                        requestToggleWishlistDto.getUserUuid(),
                        requestToggleWishlistDto.getProductUuid()
                )
                .ifPresentOrElse(
                        // 찜 목록이 존재하는 경우, 삭제
                        wishlist -> wishlistRepository.delete(wishlist),
                        // 찜 목록이 존재하지 않는 경우, 추가
                        () -> wishlistRepository.save(requestToggleWishlistDto.toEntity())
                );
    }


    /**
     * 찜 특정 상품 조회 (userUuid, productUuid)
     */
    @Override
    public ResponseReadWishlistProductDto getWishlistByProductUuid(String userUuid, String productUuid) {
        Boolean checked = wishlistRepository.existsByUserUuidAndProductUuid(userUuid, productUuid);

        return ResponseReadWishlistProductDto.from(checked);
    }
}
