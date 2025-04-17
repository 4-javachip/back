package com.starbucks.back.review.vo.in;

import com.starbucks.back.review.domain.ReviewSortType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestReviewPageVo {

    private String productUuid;
    private Integer page;
    private Integer pageSize;
    private ReviewSortType reviewSortType = ReviewSortType.LATEST;

}
