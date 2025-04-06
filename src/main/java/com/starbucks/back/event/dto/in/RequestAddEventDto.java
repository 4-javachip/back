package com.starbucks.back.event.dto.in;

import com.starbucks.back.event.domain.Event;
import com.starbucks.back.event.vo.in.RequestAddEventVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;

@Getter
@NoArgsConstructor
public class RequestAddEventDto {

    private String name;
    private String description;
    private String precaution;
    private LocalDate startAt;
    private LocalDate endAt;
    private Boolean state;

    @Builder
    public RequestAddEventDto(String name, String description, String precaution, LocalDate startAt, LocalDate endAt, Boolean state) {
        this.name = name;
        this.description = description;
        this.precaution = precaution;
        this.startAt = startAt;
        this.endAt = endAt;
        this.state = state;
    }

    public Event toEntity() {
        return Event.builder()
                .eventUuid(randomUUID().toString())
                .name(name)
                .description(description)
                .precaution(precaution)
                .startAt(startAt)
                .endAt(endAt)
                .state(state)
                .build();
    }

    public static RequestAddEventDto from(RequestAddEventVo requestAddEventVo) {
        return RequestAddEventDto.builder()
                .name(requestAddEventVo.getName())
                .description(requestAddEventVo.getDescription())
                .precaution(requestAddEventVo.getPrecaution())
                .startAt(requestAddEventVo.getStartAt())
                .endAt(requestAddEventVo.getEndAt())
                .state(requestAddEventVo.getState())
                .build();
    }

}
