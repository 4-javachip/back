package com.starbucks.back.cart.infrastructure;

import com.starbucks.back.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 장바구니 조회 by userUuid
     * @param userUuid 사용자 UUID
     * @return
     */
    List<Cart> findAllByUserUuidAndDeletedFalse(String userUuid);

    /**
     * 장바구니 겹치는지 확인
     * @param userUuid
     * @param productOptionUuid
     * @return
     */
    Boolean existsByUserUuidAndProductOptionUuidAndDeletedFalse(
            String userUuid,
            String productOptionUuid
    );

    /**
     * 장바구니 조회 by userUuid, cartUuid
     * @param cartUuid
     * @return
     */
    Optional<Cart> findByUserUuidAndCartUuid(String cartUuid, String userUuid);

    /**
     * 장바구니 리스트 조회 by cartUuidList
     * @return
     */
    List<Cart> findAllByCartUuidInAndDeletedFalse(List<String> cartUuidList);
}
