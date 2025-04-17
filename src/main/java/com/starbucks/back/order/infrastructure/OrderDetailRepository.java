package com.starbucks.back.order.infrastructure;

import com.starbucks.back.order.domain.OrderDetail;
import com.starbucks.back.order.dto.out.ResponseOrderDetailByCartUuidDto;
import com.starbucks.back.order.dto.out.ResponseReadOrderDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, OrderDetailCustomRepository {
    /**
     * 주문 상세 테이블 생성 by cartUuid
     * @return
     */
    ResponseOrderDetailByCartUuidDto getOrderDetailFromCartList(String cartUuid, String orderListUuid);

    /**
     * OrderDetail 리스트 조회by orderListUuid
     * @param orderListUuid
     */
    List<OrderDetail> findAllByOrderListUuid(String orderListUuid);
}
