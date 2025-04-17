package com.starbucks.back.review.dto.in;

import com.starbucks.back.review.domain.ReviewImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class RequestAddReviewImageDto {

    private String reviewUuid;
    private MultipartFile image;

    @Builder
    public RequestAddReviewImageDto(String reviewUuid, MultipartFile image) {
        this.reviewUuid = reviewUuid;
        this.image = image;
    }

    public ReviewImage toEntity(String imageUrl) {
        return ReviewImage.builder()
                .reviewUuid(reviewUuid)
                .imageUrl(imageUrl)
                .build();
    }

    public static RequestAddReviewImageDto of(String reviewUuid, MultipartFile image) {
        return RequestAddReviewImageDto.builder()
                .reviewUuid(reviewUuid)
                .image(image)
                .build();
    }

}
