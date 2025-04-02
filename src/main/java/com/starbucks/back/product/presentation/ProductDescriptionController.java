package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.product.application.ProductDescriptionService;
import com.starbucks.back.product.dto.in.RequestAddProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDescriptionDto;
import com.starbucks.back.product.dto.out.ResponseProductDescriptionDto;
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
     * @param requestAddProductDescriptionDto
     */
    @Operation(summary = "addProductDescription API", description = "addProductDescription API 입니다.", tags = {"Product-Description-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addProductDescription(@RequestBody RequestAddProductDescriptionDto requestAddProductDescriptionDto) {
        productDescriptionService.addProductDescription(requestAddProductDescriptionDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 uuid로 상품 설명 조회
     * @param productUuid
     */
    @Operation(summary = "getProductDescriptionByProductUuid API", description = "getProductDescriptionByProductUuid API 입니다.", tags = {"Product-Description-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseProductDescriptionVo> getProductDescriptionByProductUuid(@RequestParam String productUuid) {
        ResponseProductDescriptionDto responseProductDescriptionDto = productDescriptionService.getProductDescriptionByProductUuid(productUuid);
        return new BaseResponseEntity<>(responseProductDescriptionDto.toVo());
    }

    /**
     * 상품 설명 전체 조회
     */
    @Operation(summary = "getAllProductDescription API", description = "getAllProductDescription API 입니다.", tags = {"Product-Description-Service"})
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
    @Operation(summary = "updateProductDescription API", description = "updateProductDescription API 입니다.", tags = {"Product-Description-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateProductDescription(@RequestBody RequestProductDescriptionVo requestProductDescriptionVo) {
        productDescriptionService.updateProductDescription(RequestUpdateProductDescriptionDto.from(requestProductDescriptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 설명 삭제
     * @param requestDeleteProductDescriptionVo
     */
    @Operation(summary = "deleteProductDescription API", description = "deleteProductDescription API 입니다.", tags = {"Product-Description-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteProductDescription(@RequestBody RequestDeleteProductDescriptionVo requestDeleteProductDescriptionVo){
        productDescriptionService.deleteProductDescription(RequestDeleteProductDescriptionDto.from(requestDeleteProductDescriptionVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
