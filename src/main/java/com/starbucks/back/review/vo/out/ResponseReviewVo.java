package com.starbucks.back.review.vo.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseReviewVo {

    private String reviewUuid;
    private String userUuid;
    private String productUuid;
    private String title;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;

    @Builder
    public ResponseReviewVo(String reviewUuid, String userUuid, String productUuid, String title, String content, Integer rating, LocalDateTime createdAt) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }

}
