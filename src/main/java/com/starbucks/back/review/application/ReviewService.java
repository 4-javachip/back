package com.starbucks.back.review.application;

import com.starbucks.back.review.dto.in.RequestAddReviewDto;
import com.starbucks.back.review.dto.in.RequestDeleteReviewDto;
import com.starbucks.back.review.dto.in.RequestReviewPageDto;
import com.starbucks.back.review.dto.in.RequestUpdateReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewSummaryDto;
import org.springframework.data.domain.Page;

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
     * 상품 uuid로 리뷰 조회 (페이징)
     * @param requestReviewPageDto
     */
    Page<ResponseReviewDto> getReviewByProductUuidWithPagination(RequestReviewPageDto requestReviewPageDto);

    /**
     * 상품 리뷰 평균 평점, 개수 조회
     * @param productUuid
     */
    ResponseReviewSummaryDto getReviewSummaryByProductUuid(String productUuid);

    /**
     * 리뷰 전체 조회
     */
    List<ResponseReviewDto> getAllReviews();

    /**
     * 리뷰 수정
     * @param requestUpdateReviewDto
     */
    void updateReview(RequestUpdateReviewDto requestUpdateReviewDto);

    /**
     * 리뷰 삭제
     * @param requestDeleteReviewDto
     */
    void deleteReview(RequestDeleteReviewDto requestDeleteReviewDto);

}
