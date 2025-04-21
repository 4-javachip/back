package com.starbucks.back.review.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.vo.out.ResponseReviewVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseReviewDto {

    private String reviewUuid;
    private String userUuid;
    private String productUuid;
    private String title;
    private String content;
    private Integer rating;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @Builder
    public ResponseReviewDto(String reviewUuid, String userUuid, String productUuid, String title, String content, Integer rating, LocalDateTime createdAt) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public static ResponseReviewDto from(Review review) {
        return ResponseReviewDto.builder()
                .reviewUuid(review.getReviewUuid())
                .userUuid(review.getUserUuid())
                .productUuid(review.getProductUuid())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public ResponseReviewVo toVo() {
        return ResponseReviewVo.builder()
                .reviewUuid(reviewUuid)
                .userUuid(userUuid)
                .productUuid(productUuid)
                .title(title)
                .content(content)
                .rating(rating)
                .createdAt(createdAt)
                .build();
    }

}
