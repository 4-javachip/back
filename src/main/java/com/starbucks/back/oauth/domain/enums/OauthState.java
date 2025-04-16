package com.starbucks.back.oauth.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OauthState {
    WITHDRAWAL_PENDING("탈퇴예정"),
    ACTIVE("활성화");

    @JsonValue
    private final String label;
}
