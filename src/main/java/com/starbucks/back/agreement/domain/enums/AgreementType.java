package com.starbucks.back.agreement.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AgreementType {
    SIGN_UP("회원가입 약관"),
    SHIPPING_ADDRESS("배송지 등록 약관");

    @JsonValue  
    private final String label;
}
