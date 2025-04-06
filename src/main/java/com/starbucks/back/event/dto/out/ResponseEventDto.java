package com.starbucks.back.event.dto.out;

import com.starbucks.back.event.domain.Event;
import com.starbucks.back.event.vo.out.ResponseEventVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ResponseEventDto {

    private String eventUuid;
    private String name;
    private String description;
    private String precaution;
    private LocalDate startAt;
    private LocalDate endAt;
    private Boolean state;

    @Builder
    public ResponseEventDto(String eventUuid, String name, String description, String precaution, LocalDate startAt, LocalDate endAt, Boolean state) {
        this.eventUuid = eventUuid;
        this.name = name;
        this.description = description;
        this.precaution = precaution;
        this.startAt = startAt;
        this.endAt = endAt;
        this.state = state;
    }

    public static ResponseEventDto from(Event event) {
        return ResponseEventDto.builder()
                .eventUuid(event.getEventUuid())
                .name(event.getName())
                .description(event.getDescription())
                .precaution(event.getPrecaution())
                .startAt(event.getStartAt())
                .endAt(event.getEndAt())
                .state(event.getState())
                .build();
    }

    public ResponseEventVo toVo() {
        return ResponseEventVo.builder()
                .eventUuid(eventUuid)
                .name(name)
                .description(description)
                .precaution(precaution)
                .startAt(startAt)
                .endAt(endAt)
                .state(state)
                .build();
    }

}
