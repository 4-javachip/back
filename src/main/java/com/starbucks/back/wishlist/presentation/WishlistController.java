package com.starbucks.back.wishlist.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.wishlist.application.WishlistService;
import com.starbucks.back.wishlist.dto.in.RequestToggleWishlistDto;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;
import com.starbucks.back.wishlist.vo.in.RequestToggleWishlistVo;
import com.starbucks.back.wishlist.vo.out.ResponseReadWishlistListVo;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "GetWishlistListByUserUuid API", description = "GetWishlistListByUserUuid API 입니다.", tags = {"Wishlist-Service"})
    public BaseResponseEntity<List<ResponseReadWishlistListVo>> getWishlistListByUserUuid() {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseReadWishlistListVo> result = wishlistService.getWishlistListByUserUuid(userUuid)
                        .stream()
                        .map(ResponseReadWishlistListDto::toVo)
                        .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 찜 toggle by userUuid, productUuid, productOptionListUuid
     */
    @PostMapping
    @Operation(summary = "ToggleWishlist API", description = "ToggleWishlist API 입니다.", tags = {"Wishlist-Service"})
    public BaseResponseEntity<Void> updateWishlist(
            @RequestBody RequestToggleWishlistVo requestUpdateWishlistVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestToggleWishlistDto requestUpdateWishlistDto = RequestToggleWishlistDto.
                from(userUuid, requestUpdateWishlistVo);
        wishlistService.toggleWishlist(requestUpdateWishlistDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}

