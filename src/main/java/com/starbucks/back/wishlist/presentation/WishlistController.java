package com.starbucks.back.wishlist.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.wishlist.application.WishlistService;
import com.starbucks.back.wishlist.dto.out.ResponseReadWishlistListDto;
import com.starbucks.back.wishlist.vo.out.ResponseReadWishlistListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

