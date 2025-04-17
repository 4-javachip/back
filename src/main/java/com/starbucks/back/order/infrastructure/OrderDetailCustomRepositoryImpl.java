package com.starbucks.back.order.infrastructure;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.starbucks.back.cart.domain.QCart;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;
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
    public ResponseOrderDetailByCartUuidDto getOrderDetailFromCartList(String cartUuid, String orderListUuid) {
        QProduct product = QProduct.product;
        QProductOption productOption = QProductOption.productOption;
        QThumbnail thumbnail = QThumbnail.thumbnail;
        QCart cart = QCart.cart;

        Tuple result = jpaQueryFactory
                .select(
                        product.name,
                        productOption.price,
                        productOption.discountRate,
                        productOption.totalPrice,
                        thumbnail.thumbnailUrl,
                        cart.productQuantity
                )
                .from(cart)
                .join(productOption).on(cart.productOptionUuid.eq(productOption.productOptionUuid))
                .join(product).on(productOption.productUuid.eq(product.productUuid))
                .join(thumbnail).on(product.productUuid.eq(thumbnail.productUuid))
                .where(cart.cartUuid.eq(cartUuid))
                .fetchOne();

        if (result == null) {
           throw new BaseException(BaseResponseStatus.NO_EXIST_QUERY_FOR_ORDER_DETAIL);
        }

        return ResponseOrderDetailByCartUuidDto.from(
                orderListUuid,
                result.get(product.name),
                result.get(thumbnail.thumbnailUrl),
                result.get(productOption.price),
                result.get(productOption.discountRate),
                result.get(productOption.totalPrice),
                result.get(cart.productQuantity)
        );
    }

}
