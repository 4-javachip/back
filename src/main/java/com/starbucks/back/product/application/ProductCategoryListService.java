package com.starbucks.back.product.application;

import com.starbucks.back.product.dto.in.RequestAddProductCategoryListDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductCategoryListDto;
import com.starbucks.back.product.dto.out.ResponseProductCategoryListDto;

import java.util.List;

public interface ProductCategoryListService {

    /**
     * 상품 카테고리 리스트 추가
     * @param requestAddProductCategoryListDto
     */
    void addProductCategoryList(RequestAddProductCategoryListDto requestAddProductCategoryListDto);

    /**
     * 상품 uuid로 상품 카테고리 리스트 조회
     * @param productUuid
     */
    ResponseProductCategoryListDto getProductCategoryListByProductUuid(String productUuid);

    /**
     * 상품 카테고리 리스트 전체 조회
     * TODO : 페이징 처리 필요
     */
    List<ResponseProductCategoryListDto> getAllProductCategoryList();

    /**
     * 상품 카테고리 리스트 삭제
     * @param requestDeleteProductCategoryListDto
     */
    void deleteProductCategoryList(RequestDeleteProductCategoryListDto requestDeleteProductCategoryListDto);

}
