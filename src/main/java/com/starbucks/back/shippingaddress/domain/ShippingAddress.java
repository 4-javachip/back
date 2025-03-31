package com.starbucks.back.shippingaddress.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipping_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingAddress extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 배송지 uuid
     */
    @Column(name = "shipping_address_uuid", nullable = false, length = 100)
    private String shippingAddressUuid;

    /**
     * 주소 별칭
     */
    @Column(name = "address_name", length = 50)
    private String addressName;

    /**
     * 수령인 이름
     */
    @Column(name = "recipient_name", nullable = false, length = 50)
    private String recipientName;

    /**
     * 우편번호
     */
    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    /**
     * 기본 주소
     */
    @Column(name = "base_address", nullable = false, length = 150)
    private String baseAddress;

    /**
     * 상세 주소
     */
    @Column(name = "detail_address", nullable = false, length = 100)
    private String detailAddress;

    /**
     * 전화번호
     */
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    /**
     * 보조 전화번호
     */
    @Column(name = "second_phone_number", length = 20)
    private String secondPhoneNumber;

    /**
     * 배송 메모
     */
    @Column(name = "shipping_note", length = 1000)
    private String shippingNote;

    @Builder
    public ShippingAddress(
            Long id,
            String shippingAddressUuid,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote
    ) {
        this.id = id;
        this.shippingAddressUuid = shippingAddressUuid;
        this.addressName = addressName;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.shippingNote = shippingNote;
    }
}
