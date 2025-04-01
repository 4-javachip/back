package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.product.application.ProductService;
import com.starbucks.back.product.dto.in.RequestAddProductDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDto;
import com.starbucks.back.product.dto.out.ResponseProductDto;
import com.starbucks.back.product.vo.in.RequestDeleteProductVo;
import com.starbucks.back.product.vo.in.RequestProductVo;
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
     * @param requestAddProductDto
     */
    @Operation(summary = "AddProduct API", description = "AddProduct API 입니다.", tags = {"Product-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addProduct(@RequestBody RequestAddProductDto requestAddProductDto) {
        productService.addProduct(requestAddProductDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 이름으로 상품 조회
     * @param name
     */
    @Operation(summary = "GetProductByName API", description = "GetProductByName API 입니다.", tags = {"Product-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseProductVo> getProductByName(@RequestParam String name) {
        ResponseProductDto responseProductDto = productService.getProductByName(name);
        return new BaseResponseEntity<>(responseProductDto.toVo());
    }

    /**
     * 상품 전체 조회
     */
    @Operation(summary = "GetAllProducts API", description = "GetAllProducts API 입니다.", tags = {"Product-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseProductVo>> getAllProducts() {
        List<ResponseProductVo> result = productService.getAllProducts()
                .stream()
                .map(ResponseProductDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 수정
     * @param requestProductVo
     */
    @Operation(summary = "UpdateProduct API", description = "UpdateProduct API 입니다.", tags = {"Product-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateProduct(@RequestBody RequestProductVo requestProductVo) {
        productService.updateProduct(RequestUpdateProductDto.from(requestProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 상품 삭제
     * @param requestDeleteProductVo
     */
    @Operation(summary = "DeleteProduct API", description = "DeleteProduct API 입니다.", tags = {"Product-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteProduct(@RequestBody RequestDeleteProductVo requestDeleteProductVo) {
        productService.deleteProduct(RequestDeleteProductDto.of(requestDeleteProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
