package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserShippingAddressRepository extends JpaRepository<UserShippingAddress, Long> {

    /**
     * 유저 UUID로 배송지 Uuid 리스트 찾기
     */
    List<UserShippingAddress> findByUserUuidAndDeletedFalse(String userUuid);
}
