package com.starbucks.back.cart.infrastructure;

import com.starbucks.back.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 장바구니 UserUuid로 조회
     * @param userUuid 사용자 UUID
     * @return
     */
    List<Cart> findAllByUserUuid(String userUuid);

    /**
     * 장바구니 겹치는지 확인
     * @param userUuid
     * @param productOptionListUuid
     * @return
     */
    Boolean existsByUserUuidAndProductOptionListUuid(String userUuid, String productOptionListUuid);

    /**
     * 장바구니 UUID로 조회
     * @param cartUuid
     * @return
     */
    Optional<Cart> findByCartUuid(String cartUuid);
}
