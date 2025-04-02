package com.starbucks.back.product.application;

import com.starbucks.back.product.dto.in.RequestAddProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDescriptionDto;
import com.starbucks.back.product.dto.out.ResponseProductDescriptionDto;

import java.util.List;

public interface ProductDescriptionService {

    /**
     * 상품 설명 추가
     * @param requestAddProductDescriptionDto
     */
    void addProductDescription(RequestAddProductDescriptionDto requestAddProductDescriptionDto);

    /**
     * 상품 설명 상품 UUID로 조회
     * @param productUuid
     */
    ResponseProductDescriptionDto getProductDescriptionByProductUuid(String productUuid);

    /**
     * 상품 설명 전체 조회
     */
    List<ResponseProductDescriptionDto> getAllProductDescription();

    /**
     * 상품 설명 수정
     */
    void updateProductDescription(RequestUpdateProductDescriptionDto requestUpdateProductDescriptionDto);

    /**
     * 상품 설명 삭제
     * @param requestDeleteProductDescriptionDto
     */
    void deleteProductDescription(RequestDeleteProductDescriptionDto requestDeleteProductDescriptionDto);

}
