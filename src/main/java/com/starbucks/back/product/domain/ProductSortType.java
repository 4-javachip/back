package com.starbucks.back.product.domain;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.starbucks.back.best.domain.QBest;

public enum ProductSortType {

    /**
     * 최신순 (기본값)
     */
    NEW {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QProduct product, QProductOption productOption, QBest best) {
            return new OrderSpecifier<>(Order.DESC, product.id);
        }
    },

    /**
     * 가격 오름차순
     */
    PRICE_ASC {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QProduct product, QProductOption productOption, QBest best) {
            return new OrderSpecifier<>(Order.ASC, productOption.price);
        }
    },

    /**
     * 가격 내림차순
     */
    PRICE_DESC {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QProduct product, QProductOption productOption, QBest best) {
            return new OrderSpecifier<>(Order.DESC, productOption.price);
        }
    },

    /**
     * 추천순 (판매량 높은 순)
     */
    RECOMMEND {
        @Override
        public OrderSpecifier<?> getOrderSpecifier(QProduct product, QProductOption productOption, QBest best) {
            return new OrderSpecifier<>(Order.DESC, best.productSalesCount);
        }
    };

    public abstract OrderSpecifier<?> getOrderSpecifier(QProduct product, QProductOption productOption, QBest best);
}
