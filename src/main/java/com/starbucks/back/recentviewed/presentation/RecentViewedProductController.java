package com.starbucks.back.recentviewed.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.recentviewed.application.RecentViewedProductService;
import com.starbucks.back.recentviewed.dto.in.RequestAddRecentViewedProductDto;
import com.starbucks.back.recentviewed.dto.out.ResponseGetRecentViewedProductDto;
import com.starbucks.back.recentviewed.vo.in.RequestAddRecentViewedProductVo;
import com.starbucks.back.recentviewed.vo.out.ResponseGetRecentViewedProductVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recent-view")
@RequiredArgsConstructor
public class RecentViewedProductController {

    private final RecentViewedProductService recentViewedProductService;
    private final SecurityUtil securityUtil;

    @Operation(summary = "Add Recent Viewed Product API", description = "최근 본 상품 등록 API", tags = {"Recent-view-service"})
    @PostMapping
    public BaseResponseEntity<Void> addRecentView(
            @Valid @RequestBody RequestAddRecentViewedProductVo requestAddRecentViewedProductVo
    ) throws Exception {
        recentViewedProductService.addRecentViewedProduct(RequestAddRecentViewedProductDto.of(
                securityUtil.getCurrentUserUuid(), requestAddRecentViewedProductVo.getProductUuid()
        ));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS_RECENT_VIEW);
    }

    @Operation(summary = "Get Recent Viewed Product API", description = "최근 본 상품 목록 조회 API", tags = {"Recent-view-service"})
    @GetMapping
    public BaseResponseEntity<List<ResponseGetRecentViewedProductVo>> getRecentViews() throws Exception {
        return new BaseResponseEntity<>(
                recentViewedProductService.getRecentViewedProducts(securityUtil.getCurrentUserUuid())
                        .stream()
                        .map(ResponseGetRecentViewedProductDto::toVo)
                        .toList()
        );
    }
}
