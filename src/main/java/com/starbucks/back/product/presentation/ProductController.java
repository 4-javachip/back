package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.application.ProductService;
import com.starbucks.back.product.domain.ProductSortType;
import com.starbucks.back.product.dto.in.RequestAddProductDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDto;
import com.starbucks.back.product.dto.out.ResponseProductCategoryListDto;
import com.starbucks.back.product.dto.out.ResponseProductDto;
import com.starbucks.back.product.vo.in.RequestAddProductVo;
import com.starbucks.back.product.vo.in.RequestDeleteProductVo;
import com.starbucks.back.product.vo.in.RequestProductVo;
import com.starbucks.back.product.vo.out.ResponseProductCategoryListVo;
import com.starbucks.back.product.vo.out.ResponseProductVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 추가
     * @param requestAddProductVo
     */
    @Operation(summary = "상품 이름 추가 API", description = "상품 이름 추가 API 입니다.", tags = {"Product-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addProduct(@RequestBody RequestAddProductVo requestAddProductVo) {
        productService.addProduct(RequestAddProductDto.from(requestAddProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 UUID로 상품 조회
     * @param productUuid
     */
    @Operation(summary = "상품 uuid로 상품 이름 조회 API", description = "상품 uuid로 상품 이름 조회 API 입니다.", tags = {"Product-Service"})
    @GetMapping("/{productUuid}")
    public BaseResponseEntity<ResponseProductVo> getProductByUuid(@PathVariable("productUuid") String productUuid) {
        ResponseProductDto responseProductDto = productService.getProductByUuid(productUuid);
        return new BaseResponseEntity<>(responseProductDto.toVo());
    }

    /**
     * 상품 전체 필터링 조회
     * @param categoryId
     * @param subCategoryId
     * @param seasonId
     * @param sortType
     * @param cursor
     * @param pageSize
     * @param page
     */
    @Operation(summary = "상품 전체 필터링 조회 API", description = "상품 전체 필터링 조회(검색, 페이징) API 입니다.", tags = {"Product-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<CursorPageUtil<ResponseProductVo, Long>> getProductsByFilter(
            @RequestParam (name = "categoryId", required = false) Long categoryId,
            @RequestParam (name = "subCategoryId", required = false) Long subCategoryId,
            @RequestParam (name = "seasonId", required = false) Long seasonId,
            @RequestParam (name = "sortType", required = false, defaultValue = "NEW") ProductSortType sortType,
            @RequestParam (name = "keyword", required = false) String keyword,
            @RequestParam (name = "cursor", required = false) Long cursor,
            @RequestParam (name = "pageSize", required = false) Integer pageSize,
            @RequestParam (name = "page", required = false) Integer page
    ) {
        CursorPageUtil<ResponseProductDto, Long> dtoPage =
                productService.getAllProductsByFilter(categoryId, subCategoryId, seasonId, sortType, keyword, cursor, pageSize, page);

        return new BaseResponseEntity<>(dtoPage.map(ResponseProductDto::toVo));
    }

    /**
     * 상품 수정
     * @param requestProductVo
     */
    @Operation(summary = "상품 이름 수정 API", description = "상품 이름 수정 API 입니다.", tags = {"Product-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateProduct(@RequestBody RequestProductVo requestProductVo) {
        productService.updateProduct(RequestUpdateProductDto.from(requestProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 삭제
     * @param requestDeleteProductVo
     */
    @Operation(summary = "상품 이름 삭제 API", description = "상품 이름 삭제 API 입니다.", tags = {"Product-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteProduct(@RequestBody RequestDeleteProductVo requestDeleteProductVo) {
        productService.deleteProduct(RequestDeleteProductDto.from(requestDeleteProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
