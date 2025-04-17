package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, OrderDetailCustomRepository {
    /**
     * 주문 테이블 생성 by cartUuid
     * @return
     */
    ResponseOrderDetailByCartUuidDto getOrderDetailFromCartList(String cartUuid, String orderListUuid);

}
