package com.starbucks.back.product.infrastructure;

import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.dto.out.ResponseProductDto;

import java.util.Set;

public interface ProductCustomRepository {

    /**
     * 상품 전체 조회 페이징 처리
     * @param lastId
     * @param pageSize
     * @param page
     */
    CursorPageUtil<ResponseProductDto, Long> findAllWithPagination(
        Long lastId,
        Integer pageSize,
        Integer page,
        Set<String> bestUuids
    );

}
