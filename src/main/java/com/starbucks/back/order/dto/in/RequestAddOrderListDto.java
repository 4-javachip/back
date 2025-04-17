package com.starbucks.back.order.dto.in;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.domain.enums.PaymentStatus;
import com.starbucks.back.order.vo.in.RequestAddOrderListVo;
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
    public String userUuid;
    private List<String> checkedCartUuidList;
    private String shippingAddressUuid;
    private Integer totalOrderPrice;
    private Integer totalAmount;
    private String paymentUuid;
    private PaymentStatus paymentStatus;

    @Builder
    public RequestAddOrderListDto(
            String userUuid,
            List<String> checkedCartUuidList,
            String shippingAddressUuid,
            Integer totalOrderPrice,
            Integer totalAmount,
            String paymentUuid,
            PaymentStatus paymentStatus
    ) {
        this.userUuid = userUuid;
        this.checkedCartUuidList = checkedCartUuidList;
        this.shippingAddressUuid = shippingAddressUuid;
        this.totalOrderPrice = totalOrderPrice;
        this.totalAmount = totalAmount;
        this.paymentUuid = paymentUuid;
        this.paymentStatus = paymentStatus;
    }

    // dto => entity
    public OrderList toEntity() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomPart = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();

        int discountRate = Math.round((1 - ((float) totalAmount / totalOrderPrice)) * 100);

        return OrderList.builder()
                .orderListUuid(UUID.randomUUID().toString())
                .paymentUuid(paymentUuid)
                .orderCode(date+"-"+randomPart)
                .userUuid(userUuid)
                .discountRate(discountRate)
                .paymentStatus(paymentStatus)
                .totalOrderPrice(totalOrderPrice)
                .totalAmount(totalAmount)
                .shippingAddressUuid(shippingAddressUuid)
                .build();
    }

    // vo => dto
    public static RequestAddOrderListDto from(String userUuid, RequestAddOrderListVo requestAddOrderListVo) {
        return RequestAddOrderListDto.builder()
                .userUuid(userUuid)
                .checkedCartUuidList(requestAddOrderListVo.getCheckedCartUuidList())
                .shippingAddressUuid(requestAddOrderListVo.getShippingAddressUuid())
                .totalOrderPrice(requestAddOrderListVo.getTotalOrderPrice())
                .totalAmount(requestAddOrderListVo.getTotalAmount())
                .paymentUuid(requestAddOrderListVo.getPaymentUuid())
                .paymentStatus(requestAddOrderListVo.getPaymentStatus())
                .build();
    }
}
