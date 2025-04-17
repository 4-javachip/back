package com.starbucks.back.order.application;

import com.starbucks.back.order.dto.in.RequestAddOrderDetailDto;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import com.starbucks.back.order.infrastructure.OrderDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepository orderDetailRepository;

    /**
     * 주문 상세 추가
     */
    @Transactional
    @Override
    public void addOrderDetail(String cartUuid, String OrderListUuid) {
        ResponseOrderDetailByCartUuidDto responseOrderDetailByCartUuidDto =
                orderDetailRepository.getOrderDetailFromCartList(cartUuid, OrderListUuid);
        orderDetailRepository.save(responseOrderDetailByCartUuidDto.toEntity(OrderListUuid));
    }

    /**
     * 주문 상세 조회
     */
    @Override
    public List<ResponseReadOrderDetailDto> getOrderDetailByOrderListUuid(String orderListUuid) {
        return orderDetailRepository.findAllByOrderListUuid(orderListUuid
                ).stream()
                .map(ResponseReadOrderDetailDto::from)
                .toList();
    }
}
