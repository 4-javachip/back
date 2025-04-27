package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    /**
     * 배송지 조회 (defaulted 포함) by shippingAddressUuid
     * @param shippingAddressUuid
     * @return
     */
    Optional<ShippingAddress> findByShippingAddressUuid(String shippingAddressUuid);

    @Query("""
        SELECT new com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto(
            sa.id,
            sa.shippingAddressUuid,
            sa.addressName,
            sa.recipientName,
            sa.zipCode,
            sa.baseAddress,
            sa.detailAddress,
            sa.phoneNumber,
            sa.secondPhoneNumber,
            sa.shippingNote,
            usa.defaulted
        )
        FROM ShippingAddress sa
        JOIN UserShippingAddress usa 
            ON sa.shippingAddressUuid = usa.shippingAddressUuid
        WHERE sa.shippingAddressUuid = :uuid AND usa.deleted = false
    """)
    Optional<ResponseReadShippingAddressWithDefaultedDto> findShippingAddressWithDefaultedByShippingAddressUuid(
            @Param("uuid") String uuid);
}
