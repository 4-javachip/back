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
public class ProductCustomRepositoryImpl implements ProductCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final QProduct product = QProduct.product;

    @Override
    public CursorPageUtil<ResponseProductDto, Long> findAllWithPagination(
            Long lastId, Integer pageSize, Integer page, Set<String> bestUuids) {
        int currentPageSize = Optional.ofNullable(pageSize).orElse(DEFAULT_PAGE_SIZE);
        int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);

        BooleanBuilder builder = new BooleanBuilder();
        if (lastId != null) {
            builder.and(product.id.lt(lastId));
        }

        List<Product> result = jpaQueryFactory
                .selectFrom(product)
                .where(builder)
                .orderBy(product.id.desc())
                .limit(currentPageSize + 1)
                .fetch();

        boolean hasNext = result.size() > currentPageSize;

        if(hasNext) {
            result = result.subList(0, currentPageSize);
        }
        
        Long nextCursor = result.isEmpty() ? null : result.get(result.size() - 1).getId();


        List<ResponseProductDto> dtoList = result.stream()
                .map(p -> ResponseProductDto.of(p, bestUuids.contains(p.getProductUuid())))
                .toList();

        return CursorPageUtil.<ResponseProductDto, Long>builder()
                .content(dtoList)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .page(currentPage)
                .pageSize(currentPageSize)
                .build();
    }
}
