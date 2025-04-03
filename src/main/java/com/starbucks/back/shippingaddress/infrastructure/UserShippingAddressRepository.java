package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserShippingAddressRepository extends JpaRepository<UserShippingAddress, Long> {

    /**
     * 유저 UUID로 기본외배송지List 조회
     * @param userUuid
     * @return
     */
    List<UserShippingAddress> findByUserUuidAndDefaultedFalseAndDeletedFalse(String userUuid);

    /**
     * 유저 UUID로 기본배송지 UUID 조회
     * @param userUuid
     * @return
     */
    UserShippingAddress findByUserUuidAndDefaultedTrueAndDeletedFalse(String userUuid);
}
