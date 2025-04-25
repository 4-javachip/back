package com.starbucks.back.review.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.review.application.ReviewService;
import com.starbucks.back.review.domain.ReviewSortType;
import com.starbucks.back.review.dto.in.RequestAddReviewDto;
import com.starbucks.back.review.dto.in.RequestDeleteReviewDto;
import com.starbucks.back.review.dto.in.RequestReviewPageDto;
import com.starbucks.back.review.dto.in.RequestUpdateReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewDto;
import com.starbucks.back.review.dto.out.ResponseReviewSummaryDto;
import com.starbucks.back.review.vo.in.RequestAddReviewVo;
import com.starbucks.back.review.vo.in.RequestDeleteReviewVo;
import com.starbucks.back.review.vo.in.RequestReviewVo;
import com.starbucks.back.review.vo.out.ResponseReviewPageVo;
import com.starbucks.back.review.vo.out.ResponseReviewSummaryVo;
import com.starbucks.back.review.vo.out.ResponseReviewVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    public BaseResponseEntity<String> addReview(@RequestBody RequestAddReviewVo requestAddReviewVo) {
        String reviewUuid = reviewService.addReview(RequestAddReviewDto.of(securityUtil.getCurrentUserUuid(), requestAddReviewVo));
        return new BaseResponseEntity<>(reviewUuid);
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
     * 상품 uuid로 리뷰 조회 (페이징)
     * @param productUuid
     * @param reviewSortType
     * @param page
     * @param pageSize
     */
    @Operation(summary = "상품 uuid로 리뷰 조회 (페이징) API", description = "상품 uuid로 리뷰 조회 (페이징) API 입니다.", tags = {"Review-Service"})
    @GetMapping("/product")
    public BaseResponseEntity<ResponseReviewPageVo> getPagedReviews(
            @RequestParam(name = "productUuid") String productUuid,
            @RequestParam(name = "sortType", required = false, defaultValue = "LATEST") ReviewSortType reviewSortType,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        Page<ResponseReviewDto> result = reviewService.getReviewByProductUuidWithPagination(
                RequestReviewPageDto.of(productUuid, reviewSortType, page, pageSize)
        );

        return new BaseResponseEntity<>(ResponseReviewPageVo.from(result));
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

    /**
     * 리뷰 작성 여부 확인
     * @param orderDetailUuid
     */
    @Operation(summary = "리뷰 작성 여부 확인 API", description = "사용자가 해당 주문 상품에 대해 이미 리뷰를 작성했는지 확인합니다.", tags = {"Review-Service"})
    @GetMapping("/exist/{orderDetailUuid}")
    public ResponseEntity<Boolean> hasReview(@PathVariable("orderDetailUuid") String orderDetailUuid) {
        boolean exists = reviewService.hasReview(securityUtil.getCurrentUserUuid(), orderDetailUuid);
        return ResponseEntity.ok(exists);
    }

}
