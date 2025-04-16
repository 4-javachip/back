package com.starbucks.back.review.application.scheduler;

import com.starbucks.back.common.util.S3UploaderUtil;
import com.starbucks.back.review.domain.ReviewImage;
import com.starbucks.back.review.infrastructure.ReviewImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewImageScheduler {

    private final ReviewImageRepository reviewImageRepository;
    private final S3UploaderUtil s3UploaderUtil;

    @Scheduled(cron = "0 0 3 * * *") // 매일 오전 3시
    public void deleteExpiredReviewImages() {
        // soft delete 이후 1달 이상 된 리뷰 이미지 삭제
        LocalDateTime dateTime = LocalDateTime.now().minusMonths(1);

        List<ReviewImage> targets = reviewImageRepository.findAllByDeletedTrueAndUpdatedAtBefore(dateTime);

        log.info("S3 삭제 대상 리뷰 이미지 개수: {}", targets.size());

        for (ReviewImage image : targets) {
            try {
                s3UploaderUtil.delete(image.getImageUrl());
                log.info("S3 삭제 성공: {}", image.getImageUrl());
                reviewImageRepository.delete(image);
            } catch (Exception e) {
                log.error("S3 삭제 실패: {}", image.getImageUrl(), e);
            }
        }
    }

}
