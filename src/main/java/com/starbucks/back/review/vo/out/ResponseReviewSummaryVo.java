package com.starbucks.back.review.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReviewSummaryVo {

    private Double averageRating;
    private Long totalReviews;

    @Builder
    public ResponseReviewSummaryVo(Double averageRating, Long totalReviews) {
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

}
