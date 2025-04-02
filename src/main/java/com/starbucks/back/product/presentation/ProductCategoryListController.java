package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.product.application.ProductCategoryListService;
import com.starbucks.back.product.dto.in.RequestAddProductCategoryListDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductCategoryListDto;
import com.starbucks.back.product.dto.out.ResponseProductCategoryListDto;
import com.starbucks.back.product.vo.in.RequestDeleteProductCategoryListVo;
import com.starbucks.back.product.vo.out.ResponseProductCategoryListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product-category")
@RestController
@RequiredArgsConstructor
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    /**
     * 상품 카테고리 추가
     * @param requestAddProductCategoryListDto
     */
    @Operation(summary = "addProductCategoryList API", description = "addProductCategoryList API 입니다.", tags = {"Product-Category-List-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addProductCategoryList(@RequestBody RequestAddProductCategoryListDto requestAddProductCategoryListDto) {
        productCategoryListService.addProductCategoryList(requestAddProductCategoryListDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 uuid로 상품 카테고리 리스트 조회
     * @param productUuid
     */
    @Operation(summary = "getProductCategoryListByProductUuid API", description = "getProductCategoryListByProductUuid API 입니다.", tags = {"Product-Category-List-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseProductCategoryListVo> getProductCategoryListByProductUuid(@RequestParam String productUuid) {
        ResponseProductCategoryListDto responseProductCategoryListDto = productCategoryListService.getProductCategoryListByProductUuid(productUuid);
        return  new BaseResponseEntity<>(responseProductCategoryListDto.toVo());
    }

    /**
     * 상품 카테고리 리스트 전체 조회
     */
    @Operation(summary = "getAllProductCategoryList API", description = "getAllProductCategoryList API 입니다.", tags = {"Product-Category-List-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseProductCategoryListVo>> getAllProductCategoryList() {
        List<ResponseProductCategoryListVo> result = productCategoryListService.getAllProductCategoryList()
                .stream()
                .map(ResponseProductCategoryListDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 카테고리 리스트 삭제
     * @param requestDeleteProductCategoryListVo
     */
    @Operation(summary = "deleteProductCategoryList API", description = "deleteProductCategoryList API 입니다.", tags = {"Product-Category-List-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteProductCategoryList(@RequestBody RequestDeleteProductCategoryListVo requestDeleteProductCategoryListVo) {
        productCategoryListService.deleteProductCategoryList(RequestDeleteProductCategoryListDto.of(requestDeleteProductCategoryListVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
