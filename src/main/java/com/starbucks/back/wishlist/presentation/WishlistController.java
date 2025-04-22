package com.starbucks.back.wishlist.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.wishlist.application.WishlistService;
import com.starbucks.back.wishlist.dto.in.RequestToggleWishlistDto;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistProductDto;
import com.starbucks.back.wishlist.vo.in.RequestToggleWishlistVo;
import com.starbucks.back.wishlist.vo.out.ResponseReadWishlistListVo;
import com.starbucks.back.wishlist.vo.out.ResponseReadWishlistProductVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/wishlist")
@RestController
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final SecurityUtil securityUtil;
    /**
     * 찜 List 조회 by userUuid
     */
    @GetMapping
    @Operation(summary = "GetWishlistListByUserUuid API", description = "유저의 모든 찜 목록 조회 api", tags = {"Wishlist-Service"})
    public BaseResponseEntity<List<ResponseReadWishlistListVo>> getWishlistListByUserUuid() {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseReadWishlistListVo> result = wishlistService.getWishlistListByUserUuid(userUuid)
                        .stream()
                        .map(ResponseReadWishlistListDto::toVo)
                        .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 찜 toggle by userUuid, productUuid, productOptionUuid
     */
    @Transactional
    @PostMapping
    @Operation(summary = "ToggleWishlist API", description = "찜 버튼 클릭 api", tags = {"Wishlist-Service"})
    public BaseResponseEntity<Void> updateWishlist(
            @RequestBody RequestToggleWishlistVo requestUpdateWishlistVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestToggleWishlistDto requestUpdateWishlistDto = RequestToggleWishlistDto.
                from(userUuid, requestUpdateWishlistVo);
        wishlistService.toggleWishlist(requestUpdateWishlistDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 찜 특정 상품 조회 by userUuid, productOptionUuid
     */
    @GetMapping("/product/{productUuid}")
    @Operation(summary = "GetWishlistByUserUuidAndProductOptionUuid API", description = "특정 상품의 찜 여부 조회 api", tags = {"Wishlist-Service"})
    public BaseResponseEntity<ResponseReadWishlistProductVo> getWishlistByProductUuid(
            @PathVariable("productUuid") String productUuid
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        ResponseReadWishlistProductDto responseReadWishlistProductDto = wishlistService.getWishlistByProductUuid(userUuid, productUuid);
        return new BaseResponseEntity<>(responseReadWishlistProductDto.toVo());
    }
}

