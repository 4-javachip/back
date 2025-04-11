package com.starbucks.back.review.dto.in;

import com.starbucks.back.review.vo.in.RequestDeleteReviewVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteReviewDto {

    private String reviewUuid;
    private String userUuid;

    @Builder
    public RequestDeleteReviewDto(String reviewUuid, String userUuid) {
        this.reviewUuid = reviewUuid;
        this.userUuid = userUuid;
    }

    public static RequestDeleteReviewDto of(String userUuid, RequestDeleteReviewVo requestDeleteReviewVo) {
        return RequestDeleteReviewDto.builder()
                .reviewUuid(requestDeleteReviewVo.getReviewUuid())
                .userUuid(userUuid)
                .build();
    }

}
