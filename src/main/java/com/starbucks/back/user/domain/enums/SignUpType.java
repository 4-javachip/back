package com.starbucks.back.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignUpType {
    GENERAL("일반"),
    SOCIAL("소셜");

    @JsonValue
    private final String label;
}
