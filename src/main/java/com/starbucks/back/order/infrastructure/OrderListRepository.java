package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    /**
     * 주문 리스트 조회 by userUuid
     */
    List<OrderList> findAllByUserUuid(String userUuid);
}
