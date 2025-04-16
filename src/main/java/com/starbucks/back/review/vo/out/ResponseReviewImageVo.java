package com.starbucks.back.review.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseReviewImageVo {

    private Long id;
    private String reviewUuid;
    private String imageUrl;

    @Builder
    public ResponseReviewImageVo(Long id, String reviewUuid, String imageUrl) {
        this.id = id;
        this.reviewUuid = reviewUuid;
        this.imageUrl = imageUrl;
    }

}
