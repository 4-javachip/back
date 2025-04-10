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

import static com.starbucks.back.common.constant.PagingConstant.DEFAULT_PAGE_NUMBER;
import static com.starbucks.back.common.constant.PagingConstant.DEFAULT_PAGE_SIZE;

@Repository
@RequiredArgsConstructor
public class EventProductCustomRepositoryImpl implements EventProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QEventProduct eventProduct = QEventProduct.eventProduct;

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
        int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(eventProduct.deleted.isFalse());
        builder.and(eventProduct.eventUuid.eq(eventUuid));

        if (lastId != null) {
            builder.and(eventProduct.id.lt(lastId));
        }

        List<EventProduct> getContent = jpaQueryFactory
                .selectFrom(eventProduct)
                .where(builder)
                .orderBy(eventProduct.id.desc())
                .limit(currentPageSize + 1)
                .offset(lastId == null ? offset : 0) // cursor 방식이면 offset 생략
                .fetch();

        boolean hasNext = getContent.size() > currentPageSize;

        Long nextCursor = null;

        if (hasNext) {
            getContent = getContent.subList(0, currentPageSize);
            nextCursor = getContent.get(getContent.size() - 1).getId();
        }


        List<ResponseEventProductDto> dtoList = getContent.stream()
                .map(ResponseEventProductDto::from)
                .toList();

        return new CursorPageUtil<>(
                dtoList,
                nextCursor,
                hasNext,
                currentPageSize,
                currentPage
        );
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
        int currentPage = Optional.ofNullable(page).orElse(DEFAULT_PAGE_NUMBER);
        int offset = currentPage == 0 ? 0 : (currentPage - 1) * currentPageSize;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(eventProduct.deleted.isFalse());

        if (lastId != null) {
            builder.and(eventProduct.id.lt(lastId));
        }

        List<EventProduct> getContent = jpaQueryFactory
                .selectFrom(eventProduct)
                .where(builder)
                .orderBy(eventProduct.id.desc())
                .limit(currentPageSize + 1)
                .offset(lastId == null ? offset : 0) // cursor 방식이면 offset 생략
                .fetch();

        boolean hasNext = getContent.size() > currentPageSize;

        Long nextCursor = null;

        if (hasNext) {
            getContent = getContent.subList(0, currentPageSize);
            nextCursor = getContent.get(getContent.size() - 1).getId();
        }

        List<ResponseEventProductDto> dtoList = getContent.stream()
                .map(ResponseEventProductDto::from)
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
