package com.starbucks.back.wishlist.infrastructure;

import com.starbucks.back.wishlist.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    /**
     * 사용자 uuid로 위시리스트 조회
     *
     * @param userUuid
     * @return
     */
    List<Wishlist> findAllByUserUuidAndDeletedFalse(String userUuid);
}
