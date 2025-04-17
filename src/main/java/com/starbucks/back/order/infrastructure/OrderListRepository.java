package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {

}
