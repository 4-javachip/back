package com.starbucks.back.user_withdrwal_pending.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WithdrawalReason {
    NOT_SATISFIED("서비스 품질에 만족하지 못함"),
    TOO_MANY_EMAILS("광고가 너무 많음"),
    PRIVACY_CONCERNS("개인정보 보안 우려"),
    RARELY_USED("사용 빈도가 낮음"),
    PREFER_OTHER("다른 서비스를 사용함"),
    ETC("기타");

    @JsonValue
    private final String label;
}
