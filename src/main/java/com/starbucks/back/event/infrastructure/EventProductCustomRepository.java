package com.starbucks.back.event.infrastructure;

import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.event.dto.out.ResponseEventProductDto;

public interface EventProductCustomRepository {

    /**
     * 기획전 uuid로 삭제되지 않은 기획전 상품 리스트 조회
     * @param eventUuid
     * @param lastId
     * @param pageSize
     */
    CursorPageUtil<ResponseEventProductDto, Long> findByEventUuidWithPagination(
            String eventUuid,
            Long lastId,
            Integer pageSize
    );

    /**
     * 삭제되지 않은 기획전 상품 리스트 전체 조회
     * 관리자 페이지
     */
    CursorPageUtil<ResponseEventProductDto, Long> findAllWithPagination(
            Long lastId,
            Integer pageSize
    );

}
