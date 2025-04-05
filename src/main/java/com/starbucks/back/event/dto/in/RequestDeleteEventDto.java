package com.starbucks.back.event.dto.in;

import com.starbucks.back.event.vo.in.RequestDeleteEventVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteEventDto {

    private String eventUuid;

    @Builder
    public RequestDeleteEventDto(String eventUuid) {
        this.eventUuid = eventUuid;
    }

    public static RequestDeleteEventDto of(RequestDeleteEventVo requestDeleteEventVo) {
        return RequestDeleteEventDto.builder()
                .eventUuid(requestDeleteEventVo.getEventUuid())
                .build();
    }

}
