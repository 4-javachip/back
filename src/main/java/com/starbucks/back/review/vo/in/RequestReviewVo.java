package com.starbucks.back.review.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestReviewVo {

    private String reviewUuid;
    private String title;
    private String content;
    private Integer rating;

}
