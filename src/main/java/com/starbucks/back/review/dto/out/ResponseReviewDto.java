package com.starbucks.back.review.dto.out;

import com.starbucks.back.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseReviewDto {

    private String reviewUuid;
    private String userUuid;
    private String productUuid;
    private String title;
    private String content;
    private Integer rating;

    @Builder
    public ResponseReviewDto(String reviewUuid, String userUuid, String productUuid, String title, String content, Integer rating) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public static ResponseReviewDto from(Review review) {
        return ResponseReviewDto.builder()
                .reviewUuid(review.getReviewUuid())
                .userUuid(review.getUserUuid())
                .productUuid(review.getProductUuid())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }

    public ResponseReviewDto toVo() {
        return ResponseReviewDto.builder()
                .reviewUuid(reviewUuid)
                .userUuid(userUuid)
                .productUuid(productUuid)
                .title(title)
                .content(content)
                .rating(rating)
                .build();
    }

}
