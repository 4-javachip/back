package com.starbucks.back.best.application;

import com.starbucks.back.best.dto.in.RequestAddBestDto;
import com.starbucks.back.best.dto.in.RequestDeleteBestDto;
import com.starbucks.back.best.dto.in.RequestUpdateBestDto;
import com.starbucks.back.best.dto.out.ResponseBestDto;

import java.util.List;

public interface BestService {

    /**
     * 베스트 상품 추가
     * @param requestAddBestDto
     */
    void addBestProduct(RequestAddBestDto requestAddBestDto);

    /**
     * 베스트 상품 전체 조회 (상위 30개)
     */
    List<ResponseBestDto> getAllBestProducts();

    /**
     * 베스트 상품 수정
     * @param requestUpdateBestDto
     */
    void updateBestProduct(RequestUpdateBestDto requestUpdateBestDto);

    /**
     * 베스트 상품 삭제
     * @param requestDeleteBestDto
     */
    void deleteBestProduct(RequestDeleteBestDto requestDeleteBestDto);

}
