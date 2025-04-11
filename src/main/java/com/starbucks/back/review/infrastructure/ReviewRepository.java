package com.starbucks.back.review.infrastructure;

import com.starbucks.back.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * 리뷰 uuid로 리뷰 조회
     * @param reviewUuid
     */
    Optional<Review> findByReviewUuidAndDeletedFalse(String reviewUuid);

    /**
     * 유저 uuid로 리뷰 조회
     * @param userUuid
     */
    List<Review> findByUserUuidAndDeletedFalse(String userUuid);

    /**
     * 상품 uuid로 리뷰 조회
     * @param productUuid
     */
    List<Review> findByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 상품 평점 조회
     * @param productUuid
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productUuid = :productUuid AND r.deleted = false")
    Double findAvgRatingByProductUuidAndDeletedFalse(String productUuid);

}
