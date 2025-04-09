package com.starbucks.back.event.infrastructure;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.event.domain.EventProduct;
import com.starbucks.back.event.domain.QEventProduct;
import com.starbucks.back.event.dto.out.ResponseEventProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.starbucks.back.common.constant.PagingConstant.DEFAULT_PAGE_SIZE;

@Repository
@RequiredArgsConstructor
public class EventProductCustomRepositoryImpl implements EventProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 기획전 uuid로 삭제되지 않은 기획전 상품 리스트 조회
     * @param eventUuid
     * @param lastId
     * @param pageSize
     */
    @Override
    public CursorPageUtil<ResponseEventProductDto, Long> findByEventUuidWithPagination(
            String eventUuid,
            Long lastId,
            Integer pageSize) {

        int currentPageSize = Optional.ofNullable(pageSize).filter(size -> size > 0).orElse(DEFAULT_PAGE_SIZE);

        QEventProduct eventProduct = QEventProduct.eventProduct;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(eventProduct.deleted.isFalse());
        builder.and(eventProduct.eventUuid.eq(eventUuid));

        Optional.ofNullable(lastId)
                .ifPresent(cursor -> builder.and(eventProduct.id.lt(cursor)));

        List<EventProduct> result = jpaQueryFactory
                .selectFrom(eventProduct)
                .where(builder)
                .orderBy(eventProduct.id.desc())
                .limit(currentPageSize + 1)
                .fetch();

        boolean hasNext = result.size() > currentPageSize;

        if (hasNext) {
            result = result.subList(0, currentPageSize);
        }

        Long nextCursor = result.isEmpty() ? null : result.get(result.size() - 1).getId();

        List<ResponseEventProductDto> dtoList = result.stream()
                .map(ResponseEventProductDto::from)
                .toList();

        return CursorPageUtil.<ResponseEventProductDto, Long>builder()
                .content(dtoList)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(currentPageSize)
                .build();
    }

    /**
     * 삭제되지 않은 기획전 상품 리스트 전체 조회
     * 관리자 페이지
     */
    @Override
    public CursorPageUtil<ResponseEventProductDto, Long> findAllWithPagination(
            Long lastId,
            Integer pageSize) {

        int currentPageSize = Optional.ofNullable(pageSize).filter(size -> size > 0).orElse(DEFAULT_PAGE_SIZE);

        QEventProduct qEventProduct = QEventProduct.eventProduct;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qEventProduct.deleted.isFalse());

        if (lastId != null) {
            builder.and(qEventProduct.id.lt(lastId));
        }

        List<EventProduct> result = jpaQueryFactory
                .selectFrom(qEventProduct)
                .where(builder)
                .orderBy(qEventProduct.id.desc())
                .limit(currentPageSize + 1)
                .fetch();

        boolean hasNext = result.size() > currentPageSize;
        if (hasNext) {
            result = result.subList(0, currentPageSize);
        }

        List<ResponseEventProductDto> dtoList = result.stream()
                .map(ResponseEventProductDto::from)
                .toList();

        Long nextCursor = result.isEmpty() ? null : result.get(result.size() - 1).getId();

        return CursorPageUtil.<ResponseEventProductDto, Long>builder()
                .content(dtoList)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .pageSize(currentPageSize)
                .build();
    }

}
