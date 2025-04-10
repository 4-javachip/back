package com.starbucks.back.product.infrastructure;

import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.domain.ProductSortType;
import com.starbucks.back.product.dto.out.ResponseProductDto;

import java.util.Set;

public interface ProductCustomRepository {

    /**
     * 상품 전체 조회(페이징, 필터링)
     * @param categoryId
     * @param subCategoryId
     * @param seasonId
     * @param sortType
     * @param keyword
     * @param lastId
     * @param pageSize
     * @param page
     */
    CursorPageUtil<ResponseProductDto, Long> findByFilterWithPagination(
            Long categoryId,
            Long subCategoryId,
            Long seasonId,
            ProductSortType sortType,
            String keyword,
            Long lastId,
            Integer pageSize,
            Integer page,
            Set<String> bestUuids
    );

}
