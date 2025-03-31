package com.starbucks.back.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserState {
    DELETED(0, "탈퇴"),
    PENDING_DELETION(1, "탈퇴예정"),
    ACTIVE(2, "활성화");

    private final int code;
    @JsonValue
    private final String label;

    UserState(int code, String label) {
        this.code = code;
        this.label = label;
    }

}
