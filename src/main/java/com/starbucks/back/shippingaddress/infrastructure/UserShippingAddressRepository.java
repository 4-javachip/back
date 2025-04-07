package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserShippingAddressRepository extends JpaRepository<UserShippingAddress, Long> {

    /**
     * 기본외배송지 리스트 조회 by userUuid
     * @param userUuid
     * @return
     */
    List<UserShippingAddress> findByUserUuidAndDefaultedFalseAndDeletedFalse(String userUuid);

    /**
     * 기본배송지 조회 by userUuid
     * @param userUuid
     * @return
     */
    Optional<UserShippingAddress> findByUserUuidAndDefaultedTrueAndDeletedFalse(String userUuid);

    /**
     * 배송지리스트 조회 by userUuid
     * @param userUuid
     * @return
     */
    List<UserShippingAddress> findAllByUserUuidAndDeletedFalse(String userUuid);

    /**
     * 유저배송지 전부 삭제 by userUuid
     */
    @Modifying
    @Query("""
        UPDATE UserShippingAddress usa
        SET usa.deleted = true
        WHERE usa.userUuid = :userUuid AND usa.deleted = false
    """)
    void bulkSoftDeleteUserShippingAddressesByUserUuid(@Param("userUuid") String userUuid);

    /**
     * 유저배송지 defaulted 변경 by userUuid, shippingAddressUuid
     * @param userUuid
     * @param shippingAddressUuid
     */
    Optional<UserShippingAddress> findByUserUuidAndShippingAddressUuidAndDeletedFalse(
            String userUuid,
            String shippingAddressUuid
    );
}
