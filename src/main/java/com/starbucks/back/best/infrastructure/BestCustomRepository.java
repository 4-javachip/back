package com.starbucks.back.best.infrastructure;

import com.starbucks.back.best.domain.Best;

import java.util.List;

public interface BestCustomRepository {

    /**
     * 카테고리별 베스트 상품 조회
     * @param categoryId
     */
    List<Best> findTop30ByCategoryId(Long categoryId);

}
