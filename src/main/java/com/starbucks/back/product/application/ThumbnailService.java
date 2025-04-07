package com.starbucks.back.product.application;

import com.starbucks.back.product.dto.in.RequestAddThumbnailDto;
import com.starbucks.back.product.dto.in.RequestDeleteThumbnailDto;
import com.starbucks.back.product.dto.in.RequestUpdateThumbnailDto;
import com.starbucks.back.product.dto.out.ResponseThumbnailDto;

import java.util.List;

public interface ThumbnailService {

    /**
     * 썸네일 추가
     * @param requestAddThumbnailDto
     */
    void addThumbnail(RequestAddThumbnailDto requestAddThumbnailDto);

    /**
     * 썸네일 id로 조회
     * @param id
     */
    ResponseThumbnailDto getThumbnailById(Long id);

    /**
     * 썸네일 상품 UUID로 조회
     * @param productUuid
     */
    List<ResponseThumbnailDto> getThumbnailByProductUuid(String productUuid);

    /**
     * 썸네일 전체 조회
     */
    List<ResponseThumbnailDto> getAllThumbnails();

    /**
     * 썸네일 수정
     * @param requestUpdateThumbnailDto
     */
    void updateThumbnail(RequestUpdateThumbnailDto requestUpdateThumbnailDto);

    /**
     * 썸네일 삭제
     * @param requestDeleteThumbnailDto
     */
    void deleteThumbnail(RequestDeleteThumbnailDto requestDeleteThumbnailDto);

}
