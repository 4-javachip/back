package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.dto.in.OrderItemDto;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByOrderItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, OrderDetailCustomRepository {
    /**
     * 주문 상세 테이블 생성 by userUuid, productOptionUuid
     * @return
     */
    ResponseOrderDetailByOrderItemDto getOrderDetailFromOrderItem(OrderItemDto orderItemDto);

    /**
     * OrderDetail 리스트 조회by orderListUuid
     * @param orderListUuid
     */
    List<OrderDetail> findAllByOrderListUuid(String orderListUuid);

    /**
     * OrderDetail 조회 by orderDetail
     */
    Optional<OrderDetail> findByOrderDetailUuid(String orderDetailUuid);
}
