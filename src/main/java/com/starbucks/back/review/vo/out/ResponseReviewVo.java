package com.starbucks.back.review.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReviewVo {

    private String reviewUuid;
    private String userUuid;
    private String productUuid;
    private String title;
    private String content;
    private Integer rating;

    @Builder
    public ResponseReviewVo(String reviewUuid, String userUuid, String productUuid, String title, String content, Integer rating) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

}
