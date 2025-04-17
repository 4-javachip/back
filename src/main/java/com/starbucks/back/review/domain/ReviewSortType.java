package com.starbucks.back.review.domain;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;

@Getter
public enum ReviewSortType {

    LATEST {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QReview review) {
            return new OrderSpecifier<>(Order.DESC, review.createdAt);
        }
    },

    RATING_DESC {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QReview review) {
            return new OrderSpecifier<>(Order.DESC, review.rating);
        }
    },

    RATING_ASC {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QReview review) {
            return new OrderSpecifier<>(Order.ASC, review.rating);
        }
    };

    public abstract OrderSpecifier<?> getOrderSpecifier(QReview review);

}
