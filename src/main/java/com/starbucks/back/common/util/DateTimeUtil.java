package com.starbucks.back.common.util;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@NoArgsConstructor
public class DateTimeUtil {
    /**
     * epochSeconds (초 단위)를 LocalDateTime으로 변환합니다.
     *
     * @param epochSeconds Unix epoch seconds
     * @return 변환된 LocalDateTime
     */
    public LocalDateTime convertEpochToLocalDateTime(long epochSeconds) {
        return Instant.ofEpochSecond(epochSeconds)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}