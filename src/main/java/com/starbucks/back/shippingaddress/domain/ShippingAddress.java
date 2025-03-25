package com.starbucks.back.shippingaddress.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 100)
    private String addressName;
    @Column(nullable = false, length = 100)
    private String recipientName;
    @Column(nullable = false, length = 100)
    private String zipCode;
    @Column(nullable = false, length = 100)
    private String baseAddress;
    @Column(nullable = false, length = 100)
    private String detailAddress;
    @Column(nullable = false, length = 100)
    private String phoneNumber;
    @Column(nullable = true, length = 20)
    private String secondPhoneNumber;
    @Column(nullable = true, length = 100)
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
            String shippingNote
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
