package com.starbucks.back.product.application;

import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.domain.ProductSortType;
import com.starbucks.back.product.dto.in.RequestAddProductDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDto;
import com.starbucks.back.product.dto.out.ResponseProductDto;

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
     * 상품 UUID로 조회
     * @param productUuid
     */
    ResponseProductDto getProductByUuid(String productUuid);

    /**
     * 상품 전체 조회(페이징, 필터링)
     * @param categoryId
     * @param subCategoryId
     * @param seasonId
     * @param sortType
     * @param keyword
     * @param cursor
     * @param pageSize
     * @param page
     */
    CursorPageUtil<ResponseProductDto, Long> getAllProductsByFilter(
            Long categoryId,
            Long subCategoryId,
            Long seasonId,
            ProductSortType sortType,
            String keyword,
            Long cursor,
            Integer pageSize,
            Integer page
    );

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

    /**
     * 상품 베스트 상품 상태 업데이트
     */
    void updateBestProductStatus();

}
