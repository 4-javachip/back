package com.starbucks.back.product.application;

import com.starbucks.back.product.dto.in.RequestAddProductDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDto;
import com.starbucks.back.product.dto.out.ResponseProductDto;

import java.util.List;

public interface ProductService {

    /**
     * 상품 추가
     * @param requestAddProductDto
     */
    void addProduct(RequestAddProductDto requestAddProductDto);

    /**
     * 상품 이름으로 조회
     * @param name
     */
    ResponseProductDto getProductByName(String name);

    /**
     * 상품 전체 조회
     */
    List<ResponseProductDto> getAllProducts();

    /**
     * 상품 수정
     * @param requestUpdateProductDto
     */
    void updateProduct(RequestUpdateProductDto requestUpdateProductDto);

    /**
     * 상품 삭제
     * @param requestDeleteProductDto
     */
    void deleteProduct(RequestDeleteProductDto requestDeleteProductDto);

}
