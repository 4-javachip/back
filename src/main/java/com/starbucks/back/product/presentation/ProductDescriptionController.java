package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.product.application.ProductDescriptionService;
import com.starbucks.back.product.dto.in.RequestAddProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDescriptionDto;
import com.starbucks.back.product.dto.out.ResponseProductDescriptionDto;
import com.starbucks.back.product.vo.in.RequestAddProductDescriptionVo;
import com.starbucks.back.product.vo.in.RequestDeleteProductDescriptionVo;
import com.starbucks.back.product.vo.in.RequestProductDescriptionVo;
import com.starbucks.back.product.vo.out.ResponseProductDescriptionVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product/description")
@RestController
@RequiredArgsConstructor
public class ProductDescriptionController {

    private final ProductDescriptionService productDescriptionService;

    /**
     * 상품 설명 추가
     * @param requestAddProductDescriptionVo
     */
    @Operation(summary = "상품 설명 추가 API", description = "상품 설명 추가 API 입니다.", tags = {"Product-Description-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addProductDescription(@RequestBody RequestAddProductDescriptionVo requestAddProductDescriptionVo) {
        productDescriptionService.addProductDescription(RequestAddProductDescriptionDto.from(requestAddProductDescriptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 uuid로 상품 설명 조회
     * @param productUuid
     */
    @Operation(summary = "상품 uuid로 상품 설명 조회 API", description = "상품 uuid로 상품 설명 조회 API 입니다.", tags = {"Product-Description-Service"})
    @GetMapping("/{productUuid}")
    public BaseResponseEntity<ResponseProductDescriptionVo> getProductDescriptionByProductUuid(@PathVariable String productUuid) {
        ResponseProductDescriptionDto responseProductDescriptionDto = productDescriptionService.getProductDescriptionByProductUuid(productUuid);
        return new BaseResponseEntity<>(responseProductDescriptionDto.toVo());
    }

    /**
     * 상품 설명 전체 조회
     */
    @Operation(summary = "상품 설명 전체 조회 API", description = "상품 설명 전체 조회 API 입니다.", tags = {"Product-Description-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseProductDescriptionVo>> getAllProductDescription() {
        List<ResponseProductDescriptionVo> result = productDescriptionService.getAllProductDescription()
                .stream()
                .map(ResponseProductDescriptionDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 설명 수정
     * @param requestProductDescriptionVo
     */
    @Operation(summary = "상품 설명 수정 API", description = "상품 설명 수정 API 입니다.", tags = {"Product-Description-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateProductDescription(@RequestBody RequestProductDescriptionVo requestProductDescriptionVo) {
        productDescriptionService.updateProductDescription(RequestUpdateProductDescriptionDto.of(requestProductDescriptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 설명 삭제
     * @param requestDeleteProductDescriptionVo
     */
    @Operation(summary = "상품 설명 삭제 API", description = "상품 설명 삭제 API 입니다.", tags = {"Product-Description-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteProductDescription(@RequestBody RequestDeleteProductDescriptionVo requestDeleteProductDescriptionVo){
        productDescriptionService.deleteProductDescription(RequestDeleteProductDescriptionDto.from(requestDeleteProductDescriptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
