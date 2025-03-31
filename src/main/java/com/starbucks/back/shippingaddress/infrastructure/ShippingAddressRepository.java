package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    /**
     * 배송지 uuid로 배송지 조회
     * @param uuid
     * @return
     */
    Optional<ShippingAddress> findByShippingAddressUuidAndDeletedFalse(String uuid);
}
