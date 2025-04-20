package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.domain.enums.PaymentStatus;
import com.starbucks.back.order.vo.in.OrderItemVo;
import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
@ToString
@Getter
public class RequestAddOrderListDto {
    private String userUuid;
    private Boolean fromCart;
    private List<OrderItemVo> orderItems;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private String paymentUuid;
    private String shippingAddressUuid;

    @Builder
    public RequestAddOrderListDto(
            String userUuid,
            Boolean fromCart,
            List<OrderItemVo> orderItems,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            String paymentUuid,
            String shippingAddressUuid
        ) {
        this.userUuid = userUuid;
        this.fromCart = fromCart;
        this.orderItems = orderItems;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.paymentUuid = paymentUuid;
        this.shippingAddressUuid = shippingAddressUuid;
    }

    // dto => entity
    public OrderList toEntity(
            PaymentStatus paymentStatus,
            ResponseReadShippingAddressWithDefaultedDto shippingAddressDto
        ) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomPart = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();

        int discountRate = Math.round((1 - ((float) totalPurchasePrice / totalOriginPrice)) * 100);

        return OrderList.builder()
                .orderListUuid(UUID.randomUUID().toString())
                .paymentUuid(paymentUuid)
                .orderCode(date+"-"+randomPart)
                .userUuid(userUuid)
                .discountRate(discountRate)
                .paymentStatus(paymentStatus)
                .totalOriginPrice(totalOriginPrice)
                .totalPurchasePrice(totalPurchasePrice)
                .recipientName(shippingAddressDto.getRecipientName())
                .zipCode(shippingAddressDto.getZipCode())
                .baseAddress(shippingAddressDto.getBaseAddress())
                .detailAddress(shippingAddressDto.getDetailAddress())
                .phoneNumber(shippingAddressDto.getPhoneNumber())
                .build();
    }

    // vo => dto
    public static RequestAddOrderListDto from(String userUuid, RequestAddOrderListVo requestAddOrderListVo) {
        return RequestAddOrderListDto.builder()
                .userUuid(userUuid)
                .fromCart(requestAddOrderListVo.getFromCart())
                .orderItems(requestAddOrderListVo.getOrderItems())
                .shippingAddressUuid(requestAddOrderListVo.getShippingAddressUuid())
                .totalOriginPrice(requestAddOrderListVo.getTotalOriginPrice())
                .totalPurchasePrice(requestAddOrderListVo.getTotalPurchasePrice())
                .paymentUuid(requestAddOrderListVo.getPaymentUuid())
                .build();
    }
}
