package com.starbucks.back.review.dto.in;

import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.vo.in.RequestReviewVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateReviewDto {

    private String reviewUuid;
    private String userUuid;
    private String title;
    private String content;
    private Integer rating;

    @Builder
    public RequestUpdateReviewDto(String reviewUuid, String userUuid, String title, String content, Integer rating) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public Review updateEntity(Review review) {
        return Review.builder()
                .id(review.getId())
                .reviewUuid(review.getReviewUuid())
                .userUuid(userUuid)
                .productUuid(review.getProductUuid())
                .title(title)
                .content(content)
                .rating(rating)
                .build();
    }

    public static RequestUpdateReviewDto of(String userUuid, RequestReviewVo requestReviewVo) {
        return RequestUpdateReviewDto.builder()
                .userUuid(userUuid)
                .reviewUuid(requestReviewVo.getReviewUuid())
                .title(requestReviewVo.getTitle())
                .content(requestReviewVo.getContent())
                .rating(requestReviewVo.getRating())
                .build();
    }

}
