package com.starbucks.back.wishlist.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.wishlist.application.WishlistService;
import com.starbucks.back.wishlist.dto.in.RequestUpdateWishlistDto;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;
import com.starbucks.back.wishlist.vo.in.RequestUpdateWishlistVo;
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

    /**
     * 찜 List 조회 (userUuid)
     */
    @GetMapping("/{userUuid}")
    @Operation(summary = "GetWishlistListByUserUuid API", description = "GetWishlistListByUserUuid API 입니다.", tags = {"Wishlist-Service"})
    public BaseResponseEntity<List<ResponseReadWishlistListVo>> getWishlistListByUserUuid(@PathVariable("userUuid") String userUuid) {
        List<ResponseReadWishlistListVo> result = wishlistService.getWishlistListByUserUuid(userUuid)
                        .stream()
                        .map(ResponseReadWishlistListDto::toVo)
                        .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 찜 update
     */
    @PostMapping
    @Operation(summary = "UpdateWishlist API", description = "UpdateWishlist API 입니다.", tags = {"Wishlist-Service"})
    public BaseResponseEntity<Void> updateWishlist(@RequestBody RequestUpdateWishlistVo requestUpdateWishlistVo) {
        RequestUpdateWishlistDto requestUpdateWishlistDto = RequestUpdateWishlistDto.from(requestUpdateWishlistVo);
        wishlistService.updateWishlist(requestUpdateWishlistDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}

