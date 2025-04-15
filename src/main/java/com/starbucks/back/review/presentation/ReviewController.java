package com.starbucks.back.review.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.review.application.ReviewService;
import com.starbucks.back.review.dto.in.RequestAddReviewDto;
import com.starbucks.back.review.dto.in.RequestDeleteReviewDto;
import com.starbucks.back.review.dto.in.RequestUpdateReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewSummaryDto;
import com.starbucks.back.review.vo.in.RequestAddReviewVo;
import com.starbucks.back.review.vo.in.RequestDeleteReviewVo;
import com.starbucks.back.review.vo.in.RequestReviewVo;
import com.starbucks.back.review.vo.out.ResponseReviewSummaryVo;
import com.starbucks.back.review.vo.out.ResponseReviewVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final SecurityUtil securityUtil;

    /**
     * 리뷰 추가
     * @param requestAddReviewVo
     */
    @Operation(summary = "리뷰 추가 API", description = "리뷰 추가 API 입니다.", tags = {"Review-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addReview(@RequestBody RequestAddReviewVo requestAddReviewVo) {
        reviewService.addReview(RequestAddReviewDto.of(securityUtil.getCurrentUserUuid(), requestAddReviewVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * userUuid로 리뷰 조회
     */
    @Operation(summary = "userUuid로 리뷰 조회 API", description = "userUuid로 리뷰 조회 API 입니다.", tags = {"Review-Service"})
    @GetMapping("/user")
    public BaseResponseEntity<List<ResponseReviewVo>> getReviewsByUserUuid() {
        List<ResponseReviewVo> result = reviewService.getReviewsByUserUuid(securityUtil.getCurrentUserUuid())
                .stream()
                .map(ResponseReviewDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * reviewUuid로 리뷰 조회
     * @param reviewUuid
     */
    @Operation(summary = "reviewUuid로 리뷰 조회 API", description = "reviewUuid로 리뷰 조회 API 입니다.", tags = {"Review-Service"})
    @GetMapping("/{reviewUuid}")
    public BaseResponseEntity<ResponseReviewVo> getReviewByReviewUuid(@PathVariable("reviewUuid") String reviewUuid) {
        ResponseReviewDto responseReviewDto = reviewService.getReviewByReviewUuid(reviewUuid);
        return new BaseResponseEntity<>(responseReviewDto.toVo());
    }

    /**
     * productUuid로 리뷰 조회
     * @param productUuid
     */
    @Operation(summary = "productUuid로 리뷰 조회 API", description = "productUuid로 리뷰 조회 API 입니다.", tags = {"Review-Service"})
    @GetMapping("/product/{productUuid}")
    public BaseResponseEntity<List<ResponseReviewVo>> getReviewByProductUuid(@PathVariable("productUuid") String productUuid) {
        List<ResponseReviewVo> result = reviewService.getReviewByProductUuid(productUuid)
                .stream()
                .map(ResponseReviewDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 리뷰 평균 평점, 개수 조회
     * @param productUuid
     */
    @Operation(summary = "상품 리뷰 평균 평점, 개수 조회 API", description = "상품 리뷰 평균 평점, 개수 조회 API 입니다.", tags = {"Review-Service"})
    @GetMapping("/summary/{productUuid}")
    public BaseResponseEntity<ResponseReviewSummaryVo> getReviewSummaryByProductUuid(@PathVariable("productUuid") String productUuid) {
        ResponseReviewSummaryDto responseReviewSummaryDto = reviewService.getReviewSummaryByProductUuid(productUuid);
        return new BaseResponseEntity<>(responseReviewSummaryDto.toVo());
    }

    /**
     * 리뷰 전체 조회
     */
    @Operation(summary = "리뷰 전체 조회 API", description = "리뷰 전체 조회 API 입니다.", tags = {"Review-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseReviewVo>> getAllReviews() {
        List<ResponseReviewVo> result = reviewService.getAllReviews()
                .stream()
                .map(ResponseReviewDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 리뷰 수정
     * @param requestReviewVo
     */
    @Operation(summary = "리뷰 수정 API", description = "리뷰 수정 API 입니다.", tags = {"Review-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateReview(@RequestBody RequestReviewVo requestReviewVo) {
        reviewService.updateReview(RequestUpdateReviewDto.of(securityUtil.getCurrentUserUuid(), requestReviewVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 리뷰 삭제
     * @param requestDeleteReviewVo
     */
    @Operation(summary = "리뷰 삭제 API", description = "리뷰 삭제 API 입니다.", tags = {"Review-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteReview(@RequestBody RequestDeleteReviewVo requestDeleteReviewVo) {
        reviewService.deleteReview(RequestDeleteReviewDto.of(securityUtil.getCurrentUserUuid(), requestDeleteReviewVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
