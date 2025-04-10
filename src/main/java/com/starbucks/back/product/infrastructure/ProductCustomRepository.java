package com.starbucks.back.product.infrastructure;

import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.dto.out.ResponseProductDto;

import java.util.Set;

public interface ProductCustomRepository {

    /**
     * 상품 전체 조회
     * @param lastId
     * @param pageSize
     * @param page
     * @param bestUuids
     */
    CursorPageUtil<ResponseProductDto, Long> findAllWithPagination(
        Long lastId,
        Integer pageSize,
        Set<String> bestUuids
    );

}
