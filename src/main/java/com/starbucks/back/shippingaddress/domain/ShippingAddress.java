package com.starbucks.back.shippingaddress.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipping_address")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_name", length = 50)
    private String addressName;

    @Column(name = "recipient_name", nullable = false, length = 50)
    private String recipientName;

    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    @Column(name = "base_address", nullable = false, length = 150)
    private String baseAddress;

    @Column(name = "detail_address", nullable = false, length = 100)
    private String detailAddress;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "second_phone_number", length = 20)
    private String secondPhoneNumber;

    @Column(name = "shipping_note", length = 1000)
    private String shippingNote;

    @Builder
    public ShippingAddress(
            Long id,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote,
    ) {
        this.id = id;
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
