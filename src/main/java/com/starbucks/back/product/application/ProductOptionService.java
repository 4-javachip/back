package com.starbucks.back.product.application;

import com.starbucks.back.product.dto.in.RequestAddProductOptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductOptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductOptionDto;
import com.starbucks.back.product.dto.out.ResponseProductOptionDto;

import java.util.List;

public interface ProductOptionService {

    /**
     * 상품 옵션 추가
     * @param requestAddProductOptionDto
     */
    void addProductOption(RequestAddProductOptionDto requestAddProductOptionDto);

    /**
     * 상품 옵션 uuid로 상품 옵션 조회
     * @param productOptionUuid
     */
    ResponseProductOptionDto getProductOptionByProductOptionUuid(String productOptionUuid);

    /**
     * 상품 uuid로 상품 옵션 리스트 조회
     * @param productUuid
     */
    List<ResponseProductOptionDto> getProductOptionsByProductUuid(String productUuid);

    /**
     * 상품 옵션 리스트 전체 조회
     * TODO: 페이징 처리 필요(관리자 입장에서 보기 위해)
     */
    List<ResponseProductOptionDto> getAllProductOptions();

    /**
     * 상품 옵션 수정
     * @param requestUpdateProductOptionDto
     */
    void updateProductOption(RequestUpdateProductOptionDto requestUpdateProductOptionDto);

    /**
     * 상품 옵션 삭제
     * @param requestDeleteProductOptionDto
     */
    void deleteProductOption(RequestDeleteProductOptionDto requestDeleteProductOptionDto);

}
