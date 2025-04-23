package com.starbucks.back.review.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddReviewVo {

    private String productUuid;
    private String title;
    private String content;
    private Integer rating;
    private String orderDetailUuid;

}
