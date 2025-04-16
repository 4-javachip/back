package com.starbucks.back.review.infrastructure;

import com.starbucks.back.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    /**
     * 리뷰 uuid로 리뷰 이미지 조회
     * @param reviewUuid
     */
    List<ReviewImage> findAllByReviewUuidAndDeletedFalse(String reviewUuid);

    /**
     * S3 삭제 대상 조회
     * soft delete + updatedAt 기준 1년 경과
     * @param dateTime
     */
    List<ReviewImage> findAllByDeletedTrueAndUpdatedAtBefore(LocalDateTime dateTime);

}
