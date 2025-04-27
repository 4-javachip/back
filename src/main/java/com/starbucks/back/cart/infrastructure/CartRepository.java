package com.starbucks.back.cart.infrastructure;

import com.starbucks.back.cart.domain.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * 장바구니 리스트 조회 by userUuid
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
  
    /**
     * 장바구니 checked 전체 by userUuid
     * @return
     */
    @Modifying
    @Query("UPDATE Cart c SET c.checked = :checked WHERE c.userUuid = :userUuid AND c.deleted = false")
    void updateAllCheckedByUserUuid(@Param("userUuid") String userUuid, @Param("checked") Boolean checked);

    /**
     * 장바구니 checked by cartUuid
     * @return
     */
    List<Cart> findAllByUserUuidAndCheckedTrueAndDeletedFalse(String userUuid);

    /**
     * 장바구니 delete by userUuid, productOptionUuid
     * @return
     */
    @Modifying
    @Transactional
    @Query("UPDATE Cart c SET c.deleted = true WHERE c.userUuid = :userUuid AND c.productOptionUuid = :productOptionUuid AND c.deleted = false")
    void deleteByUserUuidAndProductOptionUuid(
            @Param("userUuid") String userUuid,
            @Param("productOptionUuid") String productOptionUuid
    );
}
