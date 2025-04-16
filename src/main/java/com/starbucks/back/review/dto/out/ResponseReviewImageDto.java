package com.starbucks.back.review.dto.out;

import com.starbucks.back.review.domain.ReviewImage;
import com.starbucks.back.review.vo.out.ResponseReviewImageVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseReviewImageDto {

    private Long id;
    private String reviewUuid;
    private String imageUrl;

    @Builder
    public ResponseReviewImageDto(Long id, String reviewUuid, String imageUrl) {
        this.id = id;
        this.reviewUuid = reviewUuid;
        this.imageUrl = imageUrl;
    }

    public static ResponseReviewImageDto from(ReviewImage reviewImage) {
        return ResponseReviewImageDto.builder()
                .id(reviewImage.getId())
                .reviewUuid(reviewImage.getReviewUuid())
                .imageUrl(reviewImage.getImageUrl())
                .build();
    }

    public ResponseReviewImageVo toVo() {
        return ResponseReviewImageVo.builder()
                .id(id)
                .reviewUuid(reviewUuid)
                .imageUrl(imageUrl)
                .build();
    }

}
