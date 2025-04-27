package com.starbucks.back.recentviewed.application;

import com.starbucks.back.recentviewed.dto.in.RequestAddRecentViewedProductDto;
import com.starbucks.back.recentviewed.dto.out.ResponseGetRecentViewedProductDto;

import java.util.List;

public interface RecentViewedProductService {

    /**
     * 사용자가 최근 본 상품을 등록한다.
     * Redis에 저장하며, 동일 상품이 존재할 경우 기존 상품은 삭제 후 새로 등록한다.
     * 리스트 길이가 MAX_COUNT를 초과할 경우, 초과된 데이터는 MySQL로 이동 후 Redis에서 삭제한다.
     */
    void addRecentViewedProduct(RequestAddRecentViewedProductDto requestAddRecentViewedProductDto) throws Exception;

    /**
     * 해당 사용자의 최근 본 상품 목록을 조회한다.
     * 리스트는 가장 최신 순으로 정렬되며, 최대 10개 반환
     */
    List<ResponseGetRecentViewedProductDto> getRecentViewedProducts(String userUuid) throws Exception;
}
