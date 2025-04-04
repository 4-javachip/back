package com.starbucks.back.best.presentation;

import com.starbucks.back.best.application.BestService;
import com.starbucks.back.best.dto.in.RequestAddBestDto;
import com.starbucks.back.best.dto.in.RequestDeleteBestDto;
import com.starbucks.back.best.dto.in.RequestUpdateBestDto;
import com.starbucks.back.best.dto.out.ResponseBestDto;
import com.starbucks.back.best.vo.in.RequestAddBestVo;
import com.starbucks.back.best.vo.in.RequestBestVo;
import com.starbucks.back.best.vo.in.RequestDeleteBestVo;
import com.starbucks.back.best.vo.out.ResponseBestVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/best")
@RestController
@RequiredArgsConstructor
public class BestController {

    private final BestService bestService;

    /**
     * 베스트 상품 생성
     * @param requestAddBestVo
     */
    @Operation(summary = "베스트 상품 생성 API", description = "베스트 상품 생성 API 입니다.", tags = {"Product-Best-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addBestProduct(@RequestBody RequestAddBestVo requestAddBestVo) {
        bestService.addBestProduct(RequestAddBestDto.from(requestAddBestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 베스트 상품 전체 조회 (상위 30개)
     */
    @Operation(summary = "상위 30개 베스트 상품 조회 API", description = "상위 30개 베스트 상품 조회 API 입니다.", tags = {"Product-Best-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseBestVo>> getAllBestProducts() {
        List<ResponseBestVo> result = bestService.getAllBestProducts()
                .stream()
                .map(ResponseBestDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     *
     * @param requestBestVo
     */
    @Operation(summary = "베스트 상품 수정 API", description = "베스트 상품 수정 API 입니다.", tags = {"Product-Best-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateBestProduct(@RequestBody RequestBestVo requestBestVo) {
        bestService.updateBestProduct(RequestUpdateBestDto.from(requestBestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 베스트 상품 삭제
     * @param requestDeleteBestVo
     */
    @Operation(summary = "베스트 상품 삭제 API", description = "베스트 상품 삭제 API 입니다.", tags = {"Product-Best-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteBestProduct(@RequestBody RequestDeleteBestVo requestDeleteBestVo) {
        bestService.deleteBestProduct(RequestDeleteBestDto.of(requestDeleteBestVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
