package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    /**
     * Uuid로 ShippingAddress 조회
     */
    Optional<ShippingAddress> findByShippingAddressUuidAndDeletedFalse(String uuid);
}
