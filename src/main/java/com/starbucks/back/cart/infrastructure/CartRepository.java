package com.starbucks.back.cart.infrastructure;

import com.starbucks.back.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 장바구니 조회
     * @param userUuid 사용자 UUID
     * @return
     */
    Optional<Cart> findByUserUuid(String userUuid);
}
