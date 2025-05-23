package com.starbucks.back.order.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    READY("준비"),
    IN_PROGRESS("진행 중"),
    WAITING_FOR_DEPOSIT("입금 대기 중"),
    DONE("결제 완료"),
    CANCELED("취소됨"),
    PARTIAL_CANCELED("부분 취소됨"),
    ABORTED("승인 실패"),
    EXPIRED("만료됨");

    private final String description;

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static com.starbucks.back.order.domain.enums.PaymentStatus from(String value) {
        for (com.starbucks.back.order.domain.enums.PaymentStatus status : PaymentStatus.values()) {
            if (status.description.equals(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("알 수 없는 결제 상태: " + value);
    }
}
