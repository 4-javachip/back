package com.starbucks.back.review.application;

import com.starbucks.back.review.dto.in.RequestAddReviewDto;
import com.starbucks.back.review.dto.in.RequestDeleteReviewDto;
import com.starbucks.back.review.dto.in.RequestUpdateReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewDto;

import java.util.List;

public interface ReviewService {

    /**
     * 리뷰 등록
     * @param requestAddReviewDto
     */
    void addReview(RequestAddReviewDto requestAddReviewDto);

    /**
     * 유저 uuid로 리뷰 조회
     * @param userUuid
     */
    List<ResponseReviewDto> getReviewsByUserUuid(String userUuid);

    /**
     * 리뷰 uuid로 리뷰 조회
     * @param reviewUuid
     */
    ResponseReviewDto getReviewByReviewUuid(String reviewUuid);

    /**
     * 상품 uuid로 리뷰 조회
     * @param productUuid
     */
    List<ResponseReviewDto> getReviewByProductUuid(String productUuid);

    /**
     * 리뷰 전체 조회
     */
    List<ResponseReviewDto> getAllReviews();

    /**
     * 리뷰 수정
     * @param userUuid
     * @param requestUpdateReviewDto
     */
    void updateReview(String userUuid, RequestUpdateReviewDto requestUpdateReviewDto);

    /**
     * 리뷰 삭제
     * @param userUuid
     * @param requestDeleteReviewDto
     */
    void deleteReview(String userUuid, RequestDeleteReviewDto requestDeleteReviewDto);

}
