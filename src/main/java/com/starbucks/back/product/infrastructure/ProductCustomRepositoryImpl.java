package com.starbucks.back.product.infrastructure;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.best.domain.QBest;
import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.domain.*;
import com.starbucks.back.product.dto.out.ResponseProductDto;
import com.starbucks.back.season.domain.QSeasonList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.starbucks.back.common.constant.PagingConstant.DEFAULT_PAGE_NUMBER;
import static com.starbucks.back.common.constant.PagingConstant.DEFAULT_PAGE_SIZE;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QProduct product = QProduct.product;
    private final QProductCategoryList productCategoryList = QProductCategoryList.productCategoryList;
    private final QProductOption productOption = QProductOption.productOption;
    private final QSeasonList seasonList = QSeasonList.seasonList;
    private final QBest best = QBest.best;

    /**
     * 상품 전체 조회(페이징, 필터링)
     * @param categoryId
     * @param subCategoryId
     * @param seasonId
     * @param sortType
     * @param lastId
     * @param pageSize
     * @param page
     */
    @Override
    public CursorPageUtil<ResponseProductDto, Long> findByFilterWithPagination(
            Long categoryId,
            Long subCategoryId,
            Long seasonId,
            ProductSortType sortType,
            String keyword,
            Long lastId,
            Integer pageSize,
            Integer page) {

        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
        int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(productCategoryList.deleted.isFalse());

        if (categoryId != null) {
            builder.and(productCategoryList.categoryId.eq(categoryId));
        }

        if (subCategoryId != null) {
            builder.and(productCategoryList.subCategoryId.eq(subCategoryId));
        }

        if (lastId != null) {
            builder.and(product.id.lt(lastId));
        }

        if (seasonId != null) {
            builder.and(seasonList.seasonId.eq(seasonId));
        }

        if (keyword != null && !keyword.isBlank()) {
            builder.and(product.name.containsIgnoreCase(keyword));
        }

        ProductSortType safeSortType = (sortType != null) ? sortType : ProductSortType.NEW;

        OrderSpecifier<?> orderSpecifier = null;
        if (sortType != null) {
            orderSpecifier = sortType.getOrderSpecifier(product, productOption, best);
        }

        List<Product> getContent = jpaQueryFactory
                .selectFrom(product)
                .leftJoin(productCategoryList).on(product.productUuid.eq(productCategoryList.productUuid))
                .leftJoin(seasonList).on(product.productUuid.eq(seasonList.productUuid))
                .leftJoin(best).on(product.productUuid.eq(best.productUuid))
                .where(builder)
                .orderBy(orderSpecifier)
                .limit(currentPageSize + 1)
                .offset(lastId == null ? offset : 0)
                .fetch();

        boolean hasNext = getContent.size() > currentPageSize;
        Long nextCursor = null;

        if (hasNext) {
            getContent = getContent.subList(0, currentPageSize);
            nextCursor = getContent.get(getContent.size() - 1).getId();
        }

        List<ResponseProductDto> dtoList = getContent.stream()
                .map(ResponseProductDto::from)
                .toList();

        return new CursorPageUtil<>(
                dtoList,
                nextCursor,
                hasNext,
                currentPageSize,
                currentPage
        );

    }
}
