package com.starbucks.back.review.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.order.application.OrderListService;
import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.dto.in.RequestAddReviewDto;
import com.starbucks.back.review.dto.in.RequestDeleteReviewDto;
import com.starbucks.back.review.dto.in.RequestReviewPageDto;
import com.starbucks.back.review.dto.in.RequestUpdateReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewSummaryDto;
import com.starbucks.back.review.infrastructure.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderListService orderListService;

    /**
     * 리뷰 등록
     * @param requestAddReviewDto
     */
    @Transactional
    @Override
    public void addReview(RequestAddReviewDto requestAddReviewDto) {
        // 유저가 상품을 구매했는지 검증
        if (!orderListService.existsOrderByUserUuidAndProductUuid(requestAddReviewDto.getUserUuid(), requestAddReviewDto.getProductUuid())) {
            throw new BaseException(BaseResponseStatus.REVIEW_NOT_ELIGIBLE);
        }

        if (reviewRepository.existsByOrderDetailUuidAndDeletedFalse(requestAddReviewDto.getOrderDetailUuid())) {
            throw new BaseException(BaseResponseStatus.REVIEW_ALREADY_EXISTS);
        }

        reviewRepository.save(requestAddReviewDto.toEntity());
    }

    /**
     * 유저 uuid로 리뷰 조회
     * @param userUuid
     */
    @Override
    public List<ResponseReviewDto> getReviewsByUserUuid(String userUuid) {
        return reviewRepository.findByUserUuidAndDeletedFalse(userUuid)
                .stream()
                .map(ResponseReviewDto::from)
                .toList();
    }

    /**
     * 리뷰 uuid로 리뷰 조회
     * @param reviewUuid
     */
    @Override
    public ResponseReviewDto getReviewByReviewUuid(String reviewUuid) {
        Review review = reviewRepository.findByReviewUuidAndDeletedFalse(reviewUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));
        return ResponseReviewDto.from(review);
    }

    /**
     * 상품 uuid로 리뷰 조회 (페이징)
     * @param requestReviewPageDto
     */
    @Override
    public Page<ResponseReviewDto> getReviewByProductUuidWithPagination(RequestReviewPageDto requestReviewPageDto) {
        Pageable pageable = PageRequest.of(requestReviewPageDto.getPage(), requestReviewPageDto.getPageSize());
        Page<Review> page = reviewRepository.findByProductUuidAndDeletedFalseWithPagination(
                requestReviewPageDto.getProductUuid(), requestReviewPageDto.getReviewSortType(), pageable);
        return page.map(ResponseReviewDto::from);
    }

    /**
     * 상품 리뷰 평균 평점, 개수 조회
     * @param productUuid
     */
    @Override
    public ResponseReviewSummaryDto getReviewSummaryByProductUuid(String productUuid) {
        return reviewRepository.findReviewSummaryByProductUuidAndDeletedFalse(productUuid);
    }

    /**
     * 리뷰 전체 조회
     */
    @Override
    public List<ResponseReviewDto> getAllReviews() {
        return reviewRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseReviewDto::from)
                .toList();
    }

    /**
     * 리뷰 수정
     * @param requestUpdateReviewDto
     */
    @Transactional
    @Override
    public void updateReview(RequestUpdateReviewDto requestUpdateReviewDto) {
        Review review = reviewRepository.findByReviewUuidAndDeletedFalse(requestUpdateReviewDto.getReviewUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));
        reviewRepository.save(requestUpdateReviewDto.updateEntity(review));
    }

    /**
     * 리뷰 삭제
     * @param requestDeleteReviewDto
     */
    @Transactional
    @Override
    public void deleteReview(RequestDeleteReviewDto requestDeleteReviewDto) {
        Review review = reviewRepository.findByReviewUuidAndDeletedFalse(requestDeleteReviewDto.getReviewUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_REVIEW));
        review.softDelete();
    }

    /**
     * 리뷰를 했는지 검증
     * @param userUuid
     * @param orderDetailUuid
     */
    @Override
    public Boolean hasReview(String userUuid, String orderDetailUuid) {
        return reviewRepository.existsByUserUuidAndOrderDetailUuidAndDeletedFalse(userUuid, orderDetailUuid);
    }

}
