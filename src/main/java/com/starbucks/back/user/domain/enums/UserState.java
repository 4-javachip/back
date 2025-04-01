package com.starbucks.back.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum UserState {
    DELETED("탈퇴"),
    PENDING_DELETION("탈퇴예정"),
    ACTIVE("활성화");

    @JsonValue
    private final String label;
}
