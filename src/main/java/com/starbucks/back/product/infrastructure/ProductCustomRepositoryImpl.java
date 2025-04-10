package com.starbucks.back.product.infrastructure;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.domain.Product;
import com.starbucks.back.product.domain.QProduct;
import com.starbucks.back.product.dto.out.ResponseProductDto;
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

    @Override
    public CursorPageUtil<ResponseProductDto, Long> findAllWithPagination(
            Long lastId, Integer pageSize, Integer page, Set<String> bestUuids) {

        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
        int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        BooleanBuilder builder = new BooleanBuilder();

        if (lastId != null) {
            builder.and(product.id.lt(lastId));
        }

        List<Product> getContent = jpaQueryFactory
                .selectFrom(product)
                .where(builder)
                .orderBy(product.id.desc())
                .limit(currentPageSize + 1)
                .offset(lastId == null ? offset : 0) // cursor 방식이면 offset 생략
                .fetch();

        boolean hasNext = getContent.size() > currentPageSize;
        Long nextCursor = null;

        if (hasNext) {
            getContent = getContent.subList(0, currentPageSize);
            nextCursor = getContent.get(getContent.size() - 1).getId();
        }

        List<ResponseProductDto> dtoList = getContent.stream()
                .map(p -> ResponseProductDto.of(p, bestUuids.contains(p.getProductUuid())))
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
