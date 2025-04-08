package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.product.application.ProductOptionService;
import com.starbucks.back.product.dto.in.RequestAddProductOptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductOptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductOptionDto;
import com.starbucks.back.product.dto.out.ResponseProductOptionDto;
import com.starbucks.back.product.vo.in.RequestAddProductOptionVo;
import com.starbucks.back.product.vo.in.RequestDeleteProductOptionVo;
import com.starbucks.back.product.vo.in.RequestProductOptionVo;
import com.starbucks.back.product.vo.out.ResponseProductOptionVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product/option")
@RestController
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    /**
     * 상품 옵션 추가
     * @param requestAddProductOptionVo
     */
    @Operation(summary = "상품 옵션 추가 API", description = "상품 옵션 추가 API 입니다.", tags = {"Product-Option-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addProductOption(@RequestBody RequestAddProductOptionVo requestAddProductOptionVo) {
        productOptionService.addProductOption(RequestAddProductOptionDto.from(requestAddProductOptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 옵션 UUID로 상품 옵션 조회
     * @param productOptionUuid
     */
    @Operation(summary = "상품 옵션 uuid로 상품 옵션 조회 API", description = "상품 옵션 uuid로 상품 옵션 조회 API 입니다.", tags = {"Product-Option-Service"})
    @GetMapping("/{productOptionUuid}")
    public BaseResponseEntity<ResponseProductOptionVo> getProductOptionByProductOptionUuid(@PathVariable("productOptionUuid") String productOptionUuid) {
        ResponseProductOptionDto responseProductOptionDto = productOptionService.getProductOptionByProductOptionUuid(productOptionUuid);
        return new BaseResponseEntity<>(responseProductOptionDto.toVo());
    }

    /**
     * 상품 UUID로 최저가인 상품 옵션 조회
     * @param productUuid
     */
    @Operation(summary = "상품 uuid로 최저가인 상품 옵션 조회 API", description = "상품 uuid로 최저가인 상품 옵션 조회 API 입니다.", tags = {"Product-Option-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseProductOptionVo> getProductOptionByProductUuidOrderByTotalPriceAsc(@RequestParam("productUuid") String productUuid) {
        ResponseProductOptionDto responseProductOptionDto = productOptionService.getProductOptionByProductUuidOrderByTotalPriceAsc(productUuid);
        return new BaseResponseEntity<>(responseProductOptionDto.toVo());
    }

    /**
     * 상품 UUID로 상품 옵션 리스트 조회
     * @param productUuid
     */
    @Operation(summary = "상품 uuid로 상품 옵션 리스트 조회 API", description = "상품 uuid로 상품 옵션 리스트 조회 API 입니다.", tags = {"Product-Option-Service"})
    @GetMapping("/list/{productUuid}")
    public BaseResponseEntity<List<ResponseProductOptionVo>> getProductOptionsByProductUuid(@PathVariable String productUuid) {
        List<ResponseProductOptionVo> result = productOptionService.getProductOptionsByProductUuid(productUuid)
                .stream()
                .map(ResponseProductOptionDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 옵션 리스트 전체 조회
     */
    @Operation(summary = "상품 옵션 리스트 전체 조회 API", description = "상품 옵션 리스트 전체 조회 API 입니다.", tags = {"Product-Option-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseProductOptionVo>> getAllProductOptions() {
        List<ResponseProductOptionVo> result = productOptionService.getAllProductOptions()
                .stream()
                .map(ResponseProductOptionDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 옵션 수정
     * @param requestProductOptionVo
     */
    @Operation(summary = "상품 옵션 수정 API", description = "상품 옵션 수정 API 입니다.", tags = {"Product-Option-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateProductOption(@RequestBody RequestProductOptionVo requestProductOptionVo) {
        productOptionService.updateProductOption(RequestUpdateProductOptionDto.from(requestProductOptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 옵션 삭제
     * @param requestDeleteProductOptionVo
     */
    @Operation(summary = "상품 옵션 삭제 API", description = "상품 옵션 삭제 API 입니다.", tags = {"Product-Option-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteProductOption(@RequestBody RequestDeleteProductOptionVo requestDeleteProductOptionVo) {
        productOptionService.deleteProductOption(RequestDeleteProductOptionDto.from(requestDeleteProductOptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
