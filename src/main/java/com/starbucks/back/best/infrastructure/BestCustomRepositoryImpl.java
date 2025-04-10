package com.starbucks.back.best.infrastructure;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.best.domain.Best;
import com.starbucks.back.best.domain.QBest;
import com.starbucks.back.product.domain.QProductCategoryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.starbucks.back.common.constant.PagingConstant.DEFAULT_PAGE_SIZE;

@Repository
@RequiredArgsConstructor
public class BestCustomRepositoryImpl implements BestCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 카테고리별 베스트 상품 조회
     * @param categoryId
     */
    @Override
    public List<Best> findTop30ByCategoryId(Long categoryId) {

        QBest best = QBest.best;
        QProductCategoryList productCategoryList = QProductCategoryList.productCategoryList;

        return jpaQueryFactory
                .selectFrom(best)
                .join(productCategoryList)
                .on(best.productUuid.eq(productCategoryList.productUuid))
                .where(productCategoryList.categoryId.eq(categoryId))
                .orderBy(best.productSalesCount.desc())
                .limit(30)
                .fetch();

    }
}
