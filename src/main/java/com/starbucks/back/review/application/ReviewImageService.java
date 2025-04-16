package com.starbucks.back.review.application;

import com.starbucks.back.review.dto.in.RequestAddReviewImageDto;
import com.starbucks.back.review.dto.out.ResponseReviewImageDto;

import java.util.List;

public interface ReviewImageService {

    /**
     * 리뷰 이미지 등록
     * @param requestAddReviewImageDto
     */
    void addReviewImages(List<RequestAddReviewImageDto> requestAddReviewImageDto);

    /**
     * reviewUuid로 리뷰 이미지 조회
     * @param reviewUuid
     */
    List<ResponseReviewImageDto> getReviewImagesByReviewUuid(String reviewUuid);

    /**
     * 리뷰 이미지 삭제
     * @param reviewUuid
     */
    void deleteReviewImagesByReviewUuid(String reviewUuid);

}
