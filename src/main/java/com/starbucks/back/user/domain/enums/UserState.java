package com.starbucks.back.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserState {
    WITHDRAWAL_PENDING("탈퇴예정"),
    ACTIVE("활성화");

    @JsonValue
    private final String label;
}
