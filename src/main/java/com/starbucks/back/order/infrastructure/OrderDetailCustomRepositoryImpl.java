package com.starbucks.back.order.infrastructure;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.cart.domain.QCart;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.order.dto.in.OrderItemDto;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByOrderItemDto;
import com.starbucks.back.product.domain.QProduct;
import com.starbucks.back.product.domain.QProductOption;
import com.starbucks.back.product.domain.QThumbnail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderDetailCustomRepositoryImpl implements OrderDetailCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ResponseOrderDetailByOrderItemDto getOrderDetailFromOrderItem(OrderItemDto orderItemDto) {
        QProduct product = QProduct.product;
        QProductOption productOption = QProductOption.productOption;
        QThumbnail thumbnail = QThumbnail.thumbnail;

        Tuple result = jpaQueryFactory
                .select(
                        product.name,
                        thumbnail.thumbnailUrl
                )
                .join(product).on(productOption.productUuid.eq(product.productUuid))
                .join(thumbnail).on(product.productUuid.eq(thumbnail.productUuid)
                        .and(thumbnail.defaulted.eq(true))
                )
                .where(productOption.productOptionUuid.eq(orderItemDto.getProductOptionUuid()))
                .fetchOne();

        if (result == null) {
           throw new BaseException(BaseResponseStatus.NO_EXIST_QUERY_FOR_ORDER_DETAIL);
        }

        return ResponseOrderDetailByOrderItemDto.from(
                orderItemDto.getOrderListUuid(),
                result.get(product.name),
                result.get(thumbnail.thumbnailUrl),
                orderItemDto.getTotalOriginPrice(),
                orderItemDto.getTotalPurchasePrice(),
                orderItemDto.getQuantity()
        );
    }

}
