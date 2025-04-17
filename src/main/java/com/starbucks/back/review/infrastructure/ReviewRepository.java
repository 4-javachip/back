package com.starbucks.back.review.infrastructure;

import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.dto.out.ResponseReviewSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {

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
     * 상품 리뷰 평균 평점, 개수 조회
     * @param productUuid
     */
    @Query("""
        SELECT new com.starbucks.back.review.dto.out.ResponseReviewSummaryDto(
            COALESCE(AVG(r.rating), 0),
            COUNT(r)
        )
        FROM Review r
        WHERE r.productUuid = :productUuid AND r.deleted = false
    """)
    ResponseReviewSummaryDto findReviewSummaryByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 리뷰 전체 조회
     */
    List<Review> findAllByDeletedFalse();

}
