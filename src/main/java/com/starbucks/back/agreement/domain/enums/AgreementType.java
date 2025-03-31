package com.starbucks.back.agreement.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AgreementType {
    SIGN_UP(0, "회원가입 약관"),
    SHIPPING_ADDRESS(1, "배송지 등록 약관");

    private final int code;
    @JsonValue  
    private final String label;

    AgreementType(int code, String label) {
        this.code = code;
        this.label = label;
    }

}
