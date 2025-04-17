package com.starbucks.back.review.infrastructure;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.review.domain.QReview;
import com.starbucks.back.review.domain.Review;
import com.starbucks.back.review.domain.ReviewSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findByProductUuidAndDeletedFalseWithPagination(String productUuid, ReviewSortType reviewSortType, Pageable pageable) {
        QReview review = QReview.review;

        // 정렬 조건 설정
        OrderSpecifier<?> orderSpecifier = null;
        if (reviewSortType != null) {
            orderSpecifier = reviewSortType.getOrderSpecifier(review);
        }

        // 리뷰 조회
        List<Review> content = jpaQueryFactory
                .selectFrom(review)
                .where(review.productUuid.eq(productUuid).and(review.deleted.isFalse()))
                .orderBy(orderSpecifier)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 전체 리뷰 개수 조회
        Long total = jpaQueryFactory
                .select(review.count())
                .from(review)
                .where(review.productUuid.eq(productUuid).and(review.deleted.isFalse()))
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0);
    }

}
