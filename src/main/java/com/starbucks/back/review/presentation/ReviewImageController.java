package com.starbucks.back.review.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.review.application.ReviewImageService;
import com.starbucks.back.review.dto.in.RequestAddReviewImageDto;
import com.starbucks.back.review.dto.out.ResponseReviewImageDto;
import com.starbucks.back.review.vo.out.ResponseReviewImageVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/review/image")
@RestController
@RequiredArgsConstructor
public class ReviewImageController {

    private final ReviewImageService reviewImageService;

    /**
     * 리뷰 이미지 추가
     * @param reviewUuid
     * @param images
     */
    @Operation(summary = "리뷰 이미지 추가 API", description = "리뷰 이미지 추가 API 입니다.", tags = {"Review-Image-Service"})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponseEntity<Void> addReviewImages(
            @RequestPart("reviewUuid") String reviewUuid,
            @RequestPart("image") List<MultipartFile> images
    ) {

        if (images.size() > 5) {
            throw new BaseException(BaseResponseStatus.TOO_MANY_REVIEW_IMAGES);
        }

        List<RequestAddReviewImageDto> dtoList = images.stream()
                .map(image -> RequestAddReviewImageDto.of(reviewUuid, image))
                .toList();

        reviewImageService.addReviewImages(dtoList);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 리뷰 uuid로 리뷰 이미지 조회
     * @param reviewUuid
     */
    @Operation(summary = "리뷰uuid로 리뷰 이미지 조회 API", description = "리뷰uuid로 리뷰 이미지 조회 API 입니다.", tags = {"Review-Image-Service"})
    @GetMapping("/list/{reviewUuid}")
    public BaseResponseEntity<List<ResponseReviewImageVo>> getReviewImagesByReviewUuid(@PathVariable("reviewUuid") String reviewUuid) {
        List<ResponseReviewImageVo> result = reviewImageService.getReviewImagesByReviewUuid(reviewUuid)
                .stream()
                .map(ResponseReviewImageDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 리뷰 이미지 삭제
     * @param reviewUuid
     */
    @Operation(summary = "리뷰 이미지 삭제 API", description = "리뷰 이미지 삭제 API 입니다.", tags = {"Review-Image-Service"})
    @DeleteMapping("/{reviewUuid}")
    public BaseResponseEntity<Void> deleteReviewImagesByReviewUuid(@PathVariable("reviewUuid") String reviewUuid) {
        reviewImageService.deleteReviewImagesByReviewUuid(reviewUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
