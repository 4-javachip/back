package com.starbucks.back.review.dto.in;

import com.starbucks.back.review.domain.ReviewSortType;
import com.starbucks.back.review.vo.in.RequestReviewPageVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestReviewPageDto {

    private String productUuid;
    private Integer page;
    private Integer pageSize;
    private ReviewSortType reviewSortType;

    @Builder
    public RequestReviewPageDto(String productUuid, Integer page, Integer pageSize, ReviewSortType reviewSortType) {
        this.productUuid = productUuid;
        this.page = page;
        this.pageSize = pageSize;
        this.reviewSortType = reviewSortType;
    }

    public static RequestReviewPageDto of(String productUuid, ReviewSortType reviewSortType, Integer page, Integer pageSize) {
        return RequestReviewPageDto.builder()
                .productUuid(productUuid)
                .page(page)
                .pageSize(pageSize)
                .reviewSortType(reviewSortType != null ? reviewSortType : ReviewSortType.LATEST)
                .build();
    }

}
