package com.starbucks.back.payment.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    READY("준비"),
    IN_PROGRESS("진행 중"),
    SUCCESS("성공"),
    FAIL("실패");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static PaymentStatus from(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.description.equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("알 수 없는 결제 상태: " + value);
    }
}
