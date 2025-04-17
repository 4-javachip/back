package com.starbucks.back.review.infrastructure;

import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.domain.ReviewSortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewCustomRepository {

    /**
     * 상품 uuid로 리뷰 조회 (페이징)
     * @param productUuid
     * @param reviewSortType
     * @param pageable
     */
    Page<Review> findByProductUuidAndDeletedFalseWithPagination(String productUuid, ReviewSortType reviewSortType, Pageable pageable);

}
