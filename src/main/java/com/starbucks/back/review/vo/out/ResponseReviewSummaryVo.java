package com.starbucks.back.review.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReviewSummaryVo {

    private String averageRating;
    private Long totalReviews;

    @Builder
    public ResponseReviewSummaryVo(String averageRating, Long totalReviews) {
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

}
